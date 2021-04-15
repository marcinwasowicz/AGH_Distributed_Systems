package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientData{
    private HashMap<Integer, Socket> clients;
    private HashMap<Integer, Thread> clientThreads;

    public ClientData(){
        this.clients = new HashMap<>();
        this.clientThreads = new HashMap<>();
    }

    public synchronized void addClient(int clientID, Socket connection, Thread thread){
        this.clients.put(clientID, connection);
        this.clientThreads.put(clientID, thread);
    }

    public synchronized void removeClient(int clientID){
        this.clients.remove(clientID);
        this.clientThreads.remove(clientID);

    }

    public synchronized void sendMessage(Message message){
        for(Map.Entry<Integer, Socket> client : clients.entrySet()){
            if(client.getKey() == message.getClientID()){
                continue;
            }
            try {
                new PrintWriter(client.getValue().getOutputStream(), true).println(message);
            } catch (IOException ignored) {
            }
        }
    }

    public synchronized void sendDatagram(DatagramPacket packet, DatagramSocket socket){
        for(Map.Entry<Integer, Socket> client : clients.entrySet()){

            int senderPort = packet.getPort();
            int clientPort = client.getValue().getPort();

            InetAddress senderAddress = packet.getAddress();
            InetAddress clientAddress = client.getValue().getInetAddress();

            if(clientAddress.equals(senderAddress) && senderPort == clientPort){
                continue;
            }
            try {
                socket.send(new DatagramPacket(packet.getData(), packet.getLength(), clientAddress, clientPort));
            } catch (IOException ignored){
            }
        }
    }
}
