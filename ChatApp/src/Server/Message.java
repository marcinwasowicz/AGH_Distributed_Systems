package Server;

public class Message {
    private final int clientID;
    private final String text;

    public Message(int clientID, String text){
        this.clientID = clientID;
        this.text = text;
    }

    public int getClientID() {
        return clientID;
    }

    @Override
    public String toString() {
        return "Client " + this.clientID + ": " + this.text;
    }
}
