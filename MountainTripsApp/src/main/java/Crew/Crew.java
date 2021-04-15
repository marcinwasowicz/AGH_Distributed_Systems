package Crew;

import SystemManager.SystemManager;

import java.io.IOException;
import java.util.Scanner;

public class Crew {
    private String crewName;
    private Scanner ordersInput;
    private SystemManager systemManager;
    private boolean running;
    private String ackTag;
    private String adminCommunicationTag;

    public Crew(String crewName, Scanner ordersInput, SystemManager systemManager){
        this.crewName = crewName;
        this.ordersInput = ordersInput;
        this.systemManager = systemManager;
        this.running = true;
        this.ackTag = null;
        this.adminCommunicationTag = null;
    }

    public void run(){
        try {
            this.ackTag = this.systemManager.initAcknowledgementsQueue(this.crewName);
            this.adminCommunicationTag = this.systemManager.initAdminQueue(false);
            this.systemManager.acceptAcknowledgements(this.ackTag, this.crewName);
            this.systemManager.acceptAdminMessages(this.adminCommunicationTag, this.crewName);
        } catch (IOException e) {
            System.err.println(this.crewName + " will not be served as an error occurred while creating ack/admin queue for this crew");
            this.running = false;
            e.printStackTrace();
        }

        while(this.running){
            String[] orders = this.ordersInput.nextLine().strip().split(" ");
            for(String order : orders){
                if(order.equals("exit")){
                    this.running = false;
                    break;
                }

                try {
                    this.systemManager.postOrder(order, this.crewName);
                } catch (IOException e) {
                    System.err.println("Error occurred while posting order for " + order + " for crew " + this.crewName);
                    e.printStackTrace();
                }
            }
        }
    }
}
