package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientConnection;
    private int clientID;
    private ClientData clientData;
    private MessageQueue messageQueue;
    private boolean running;

    public ClientHandler(int clientID, Socket clientConnection, ClientData clientData, MessageQueue messageQueue){
        this.clientID = clientID;
        this.clientConnection = clientConnection;
        this.messageQueue = messageQueue;
        this.clientData = clientData;
        this.running = true;
    }

    public void run(){
        this.clientData.addClient(this.clientID, this.clientConnection, Thread.currentThread());
        while(this.running){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.clientConnection.getInputStream()));
                String message = reader.readLine();
                if(message != null) {
                    messageQueue.addMessage(clientID, message);
                }
            } catch (IOException e) {
                this.running = false;
            }
        }

        try {
            if(!this.clientConnection.isClosed()){
                this.clientConnection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.clientData.removeClient(this.clientID);
    }
}