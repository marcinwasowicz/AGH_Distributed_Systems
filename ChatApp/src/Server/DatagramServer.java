package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramServer implements Runnable{
    private DatagramSocket socket;
    private ClientData clientData;
    private boolean running;
    private int bufferLength;

    public DatagramServer(int port, ClientData clientData, int bufferLength) throws SocketException {
        this.clientData = clientData;
        this.socket = new DatagramSocket(port);
        this.running = true;
        this.bufferLength = bufferLength;
    }

    @Override
    public void run() {
        while (this.running) {
            try {
                DatagramPacket packet = new DatagramPacket(new byte[this.bufferLength], this.bufferLength);
                this.socket.receive(packet);
                this.clientData.sendDatagram(packet, this.socket);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
