package Supplier;

import SystemManager.SystemManager;

import java.io.IOException;
import java.util.Random;

public class Supplier {
    private String supplierName;
    private String[] orderTypes;
    private SystemManager systemManager;
    private String adminCommunicationTag;
    private Random ackIDGenerator = new Random();

    public Supplier(String supplierName, String[] orderTypes, SystemManager systemManager){
        this.supplierName = supplierName;
        this.orderTypes = orderTypes;
        this.systemManager = systemManager;
        this.adminCommunicationTag = null;
    }

    public void run(){
        try {
            this.adminCommunicationTag = this.systemManager.initAdminQueue(true);
            this.systemManager.acceptAdminMessages(this.adminCommunicationTag, this.supplierName);
        } catch (IOException e) {
            System.err.println(this.supplierName + " will not be served as an error occurred while creating ack/admin queue for this supplier");
            e.printStackTrace();
            return;
        }

        for(String orderType : this.orderTypes){
            try {
                this.systemManager.initOrdersQueue(orderType);
                this.systemManager.acceptOrders(orderType, this.supplierName, this.ackIDGenerator);
            } catch (IOException e) {
                System.err.println(
                        this.supplierName + " will not be served for order type "
                                + orderType + " as an error occurred while initializing queue"
                );
                e.printStackTrace();
            }
        }
    }
}
