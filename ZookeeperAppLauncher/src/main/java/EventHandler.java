import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import java.io.IOException;

public class EventHandler implements Watcher {

    private final String watchedNodePath;
    private final String executablePath;
    private final Application application;
    private final Runtime runtime;
    private Process childExecutable;

    public EventHandler(Application application, String watchedNodePath, String executablePath){
        this.watchedNodePath = watchedNodePath;
        this.application = application;
        this.executablePath = executablePath;
        this.runtime = Runtime.getRuntime();
        this.childExecutable = null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState() == Event.KeeperState.Expired){
            this.application.stop();
            return;
        }

        if(watchedEvent.getPath().equals(this.watchedNodePath)){
            switch (watchedEvent.getType()){
                case NodeCreated:
                    this.launchExternalApplication();
                    break;
                case NodeDeleted:
                    this.closeExternalApplication();
                    break;
                default:
                    break;
            }
            return;
        }

        if(watchedEvent.getPath().startsWith(this.watchedNodePath)){
            switch (watchedEvent.getType()){
                case NodeCreated:
                case NodeDeleted:
                    this.application.getNodeChildrenCount(this.watchedNodePath);
                    break;
                default:
                    break;
            }
        }
    }

    private void launchExternalApplication(){
        try {
            this.childExecutable = this.runtime.exec(this.executablePath);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void closeExternalApplication(){
        if(this.childExecutable != null){
            this.childExecutable.destroy();
            try {
                this.childExecutable.waitFor();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            this.childExecutable = null;
        }
    }
}
