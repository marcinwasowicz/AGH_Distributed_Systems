package Server;

import java.util.ArrayList;

public class MessageQueue{
    private ArrayList<Message> messages;

    public MessageQueue(){
        this.messages = new ArrayList<>();
    }

    public synchronized void addMessage(int clientID, String message){
        this.messages.add(new Message(clientID, message));
        notify();
    }

    public synchronized ArrayList<Message> getMessages() throws InterruptedException {
        if(this.messages.size() == 0){
            wait();
        }
        ArrayList<Message> messagesCopy = this.messages;
        this.messages = new ArrayList<>();
        return messagesCopy;
    }

}