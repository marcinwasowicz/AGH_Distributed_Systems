package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private int port;
    private ServerSocket socket;
    private ClientData clientData;
    private MessageQueue messageQueue;
    private int autoID;
    private boolean running;
    private DatagramServer datagramServer;

    public Server(int port, int datagramLength) throws IOException {
        this.port = port;
        this.socket = new ServerSocket(this.port);
        this.clientData = new ClientData();
        this.messageQueue =  new MessageQueue();
        this.datagramServer = new DatagramServer(port, this.clientData, datagramLength);
        this.autoID = 0;
        this.running = true;
    }

    public void runServer() {
        Thread messageHandler = new Thread(new MessageHandler(this.clientData, this.messageQueue));
        messageHandler.start();

        Thread datagramServerThread = new Thread(this.datagramServer);
        datagramServerThread.start();


        while(this.running){
            try {
                Socket clientConnection = this.socket.accept();
                Thread handler = new Thread(new ClientHandler(this.getAutoID(), clientConnection, clientData, messageQueue));
                handler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getAutoID(){
        this.autoID++;
        return this.autoID - 1;
    }

}
