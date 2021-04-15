package Server;

import java.io.IOException;

public class ServerApp{
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        int datagramLength = Integer.parseInt(args[1]);
        try {
            Server server = new Server(port, datagramLength);
            server.runServer();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
