package Client;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private InetAddress serverAddress;
    private int serverPort;
    private Scanner userInput;
    private Socket serverSocket;
    private DatagramSocket datagramSocket;
    private int datagramLength;
    private MulticastSocket groupSocket;
    private InetAddress groupAddress;
    private int groupPort;

    public Client(InetAddress serverAddress, int serverPort, InetAddress groupAddress ,int groupPort, int datagramLength) throws IOException {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.userInput = new Scanner(System.in);
        this.serverSocket = new Socket(this.serverAddress, this.serverPort);
        this.datagramSocket = new DatagramSocket(this.serverSocket.getLocalPort());
        this.datagramLength = datagramLength;
        this.groupAddress = groupAddress;
        this.groupPort = groupPort;
        this.groupSocket = new MulticastSocket(groupPort);
        this.groupSocket.joinGroup(groupAddress);
    }

    public void session() throws IOException, InterruptedException {

        Thread reader = new Thread(new Reader(this.serverSocket));
        Thread writer = new Thread(new Writer(this.serverSocket,this.datagramSocket,this.userInput, this.datagramLength, this.groupAddress, this.groupPort));
        Thread datagramReader = new Thread(new DatagramReader(this.datagramSocket, this.datagramLength));
        Thread multicastReader = new Thread(new DatagramReader(this.groupSocket, this.datagramLength));

        reader.start();
        datagramReader.start();
        multicastReader.start();
        writer.start();

        reader.join();
        this.datagramSocket.close();
        datagramReader.join();
        this.groupSocket.close();
        multicastReader.join();
        this.userInput.close();
        writer.join();
    }
}
