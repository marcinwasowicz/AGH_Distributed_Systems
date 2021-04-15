package Admin;

import SystemManager.SystemManager;

import java.io.IOException;
import java.util.Scanner;

public class Admin {
    private String systemName;
    private SystemManager systemManager;
    private Scanner adminInput;
    private boolean running;

    public Admin(String systemName, SystemManager systemManager, Scanner adminInput){
        this.systemName = systemName;
        this.systemManager = systemManager;
        this.adminInput = adminInput;
        this.running = true;
    }

    public void run(){
        try {
            this.systemManager.initSystemQueue(this.systemName);
            this.systemManager.acceptSystemMessages(this.systemName);
        } catch (IOException e){
            this.running = false;
            System.err.println("Failed to initialize queues for admin. Service will not be working");
            e.printStackTrace();
        }

        while(this.running){
            String message = this.adminInput.nextLine();
            try {
                if(message.startsWith("crew:")){
                    this.systemManager.postAdminMessage(message, false, true);
                }
                else if(message.startsWith("supplier:")){
                    this.systemManager.postAdminMessage(message, true, false);
                }
                else{
                    this.systemManager.postAdminMessage(message, true, true);
                }
            } catch (IOException e){
                System.err.println("Error occured while sending " + message);
            }
        }
    }
}
