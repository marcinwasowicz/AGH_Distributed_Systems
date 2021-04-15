import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Application implements Runnable{

    private final ZooKeeper serviceConnection;
    private final CommandListener commandListener;
    private final Thread commandListenerThread;
    private boolean addedWatcher;
    private boolean running;

    public Application(String hosts, int timeOut, String nodePath, String executablePath) throws IOException{
        EventHandler eventHandler = new EventHandler(this, nodePath, executablePath);

        this.running = true;
        this.addedWatcher = false;
        this.commandListener = new CommandListener(this, nodePath);
        this.commandListenerThread = new Thread(this.commandListener);
        this.serviceConnection = new ZooKeeper(hosts, timeOut, eventHandler);
        this.addNodeWatcher(nodePath);
    }

    @Override
    public void run() {
        this.commandListenerThread.start();
        synchronized (this){
            while(this.running){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop(){
        synchronized (this) {
            this.commandListener.stop();
            this.running = false;
            notify();
        }
    }

    public void getNodeChildrenCount(String nodePath){
        try {
            System.out.println(this.serviceConnection.getAllChildrenNumber(nodePath));
        } catch (KeeperException e) {
            this.handleServiceFailure();
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getNodeTreeStructure(String nodePath){
        ArrayList<String> nodeTreeStructure = new ArrayList<>();
        try {
            this.nodeDFS(nodePath, nodeTreeStructure);
        } catch (KeeperException e) {
            this.handleServiceFailure();
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return nodeTreeStructure;
    }

    private void addNodeWatcher(String nodePath){
        while(!this.addedWatcher) {
            try {
                this.addedWatcher = true;
                this.serviceConnection.addWatch(nodePath, AddWatchMode.PERSISTENT_RECURSIVE);
            } catch (InterruptedException | KeeperException e) {
                this.addedWatcher = false;
                e.printStackTrace();
            }
        }
    }

    private void handleServiceFailure(){
        ZooKeeper.States serviceState = this.serviceConnection.getState();
        if(!serviceState.isConnected() || !serviceState.isAlive()){
            this.stop();
        }
    }

    private void nodeDFS(String nodePath, ArrayList<String> treeStructureBuffer) throws InterruptedException, KeeperException {
        Stack<String> nodeDFSStack = new Stack<>();
        nodeDFSStack.push(nodePath);
        while(!nodeDFSStack.empty()){
            String currentRootPath = nodeDFSStack.pop();
            for(String childPath : this.serviceConnection.getChildren(currentRootPath, false)){
                nodeDFSStack.push(String.join("/", currentRootPath, childPath));
            }
            treeStructureBuffer.add(currentRootPath);
        }
    }
}
