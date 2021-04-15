package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args)  {

        try{
            String serverName = args[0];
            String groupName = args[1];
            int serverPort = Integer.parseInt(args[2]);
            int groupPort = Integer.parseInt(args[3]);
            int datagramLength = Integer.parseInt(args[4]);

            InetAddress serverAddress = InetAddress.getByName(serverName);
            InetAddress groupAddress = InetAddress.getByName(groupName);

            Client client = new Client(serverAddress, serverPort,groupAddress, groupPort, datagramLength);
            client.session();
        } catch(UnknownHostException e){
            System.err.println("Invalid server name");
        } catch(IOException e){
            System.err.println("Unable to connect to server");
        } catch(InterruptedException e) {
            System.err.println("Threading error");
        }
    }
}
