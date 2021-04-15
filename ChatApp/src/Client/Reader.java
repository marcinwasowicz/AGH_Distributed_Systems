package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Reader implements Runnable{
    private BufferedReader serverInput;
    private Socket serverSocket;
    private boolean running;
    public Reader(Socket serverSocket) throws IOException{
        this.serverInput = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        this.serverSocket = serverSocket;
        this.running = true;
    }

    public void run(){
        while(this.running){
            try {
                String serverMessage = this.serverInput.readLine();
                if(serverMessage != null){
                    System.out.println(serverMessage);
                }
            } catch (IOException e) {
                this.running = false;
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
}
