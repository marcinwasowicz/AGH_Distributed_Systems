package Server;

import java.util.ArrayList;

public class MessageHandler implements Runnable{
    private ClientData clientData;
    private MessageQueue messageQueue;
    private boolean running;

    public MessageHandler(ClientData clientData, MessageQueue messageQueue){
        this.clientData = clientData;
        this.messageQueue = messageQueue;
        this.running = true;
    }

    public void run(){
        while(this.running){
            try {
                ArrayList<Message> messages = this.messageQueue.getMessages();
                for(Message message : messages){
                    this.clientData.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
