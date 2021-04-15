package Supplier;

import SystemManager.SystemManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class SupplierApp {
    public static void main(String[] args) {
        String host = args[0];
        String supplierName = args[1];
        String[] orderTypes = Arrays.copyOfRange(args, 2, args.length);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        SystemManager systemManager;
        Connection connection;
        Channel channel;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            systemManager = new SystemManager(channel);
            systemManager.initSystem();
        } catch (IOException | TimeoutException e) {
            System.err.println("error occurred when connecting to message broker");
            e.printStackTrace();
            return;
        }

        Supplier supplier = new Supplier(supplierName, orderTypes, systemManager);
        supplier.run();
    }
}
