import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandListener implements Runnable{

    private final Application application;
    private final String nodePath;
    private final BufferedReader inputBuffer;
    private boolean running;

    public CommandListener(Application application, String nodePath){
        this.application = application;
        this.nodePath = nodePath;
        this.inputBuffer = new BufferedReader(new InputStreamReader(System.in));
        this.running = true;
    }

    @Override
    public void run() {
        while (this.running){
            try {
                this.inputBuffer.readLine();
                ArrayList<String> nodeTreeStructure = this.application.getNodeTreeStructure(this.nodePath);
                for(String child : nodeTreeStructure){
                    System.out.println(child);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        try {
            this.running = false;
            this.inputBuffer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
