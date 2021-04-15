package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReader implements Runnable{
    private DatagramSocket socket;
    private int bufferLength;
    private boolean running;

    public DatagramReader(DatagramSocket socket, int bufferLength){
        this.socket = socket;
        this.bufferLength = bufferLength;
        this.running = true;
    }

    @Override
    public void run() {
        while(this.running){
            DatagramPacket packet = new DatagramPacket(new byte[this.bufferLength], this.bufferLength);
            try {
                this.socket.receive(packet);
                System.out.println(new String(packet.getData()));
            } catch (IOException e) {
                this.running = false;
            }
        }
    }
}
