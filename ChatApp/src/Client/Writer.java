package Client;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Writer implements Runnable{
    private PrintWriter serverOutput;
    private Scanner userInput;
    private Socket serverSocket;
    private boolean running;
    private DatagramSocket datagramSocket;
    private int bufferLength;
    private InetAddress groupAddress;
    private int groupPort;


    public Writer(Socket serverSocket, DatagramSocket datagramSocket, Scanner userInput, int bufferLength, InetAddress groupAddress, int groupPort) throws IOException{
        this.serverOutput = new PrintWriter(serverSocket.getOutputStream(), true);
        this.userInput = userInput;
        this.serverSocket = serverSocket;
        this.running = true;
        this.datagramSocket = datagramSocket;
        this.bufferLength = bufferLength;
        this.groupAddress = groupAddress;
        this.groupPort = groupPort;
    }


    public void run(){
        while(this.running){
            try {
                String userMessage = this.userInput.nextLine();
                if(userMessage.equals("UDP")){
                    DatagramPacket packet = this.generateUDPMessage();
                    this.datagramSocket.send(packet);
                }
                else if(userMessage.equals("MUDP")){
                    DatagramPacket packet = this.generateMulticastMessage();
                    this.datagramSocket.send(packet);
                }
                else {
                    this.serverOutput.println(userMessage);
                }
            } catch (IllegalStateException e){
                this.running = false;
            } catch (IOException ignored){
            }
        }

        if(!this.serverSocket.isClosed()){
            try {
                this.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private DatagramPacket generateUDPMessage(){
        byte[] udpMessage = new byte[this.bufferLength];
        new Random().nextBytes(udpMessage);

        InetAddress serverAddress = this.serverSocket.getInetAddress();
        int serverPort = this.serverSocket.getPort();

        return new DatagramPacket(udpMessage, this.bufferLength, serverAddress, serverPort);
    }

    private DatagramPacket generateMulticastMessage(){
        byte[] multicastMessage = new byte[this.bufferLength];
        new Random().nextBytes(multicastMessage);

        return new DatagramPacket(multicastMessage, this.bufferLength, groupAddress, groupPort);
    }
}
