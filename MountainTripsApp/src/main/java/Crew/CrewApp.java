package Crew;

import SystemManager.SystemManager;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class CrewApp {
    public static void main(String[] args) {
        String host = args[0];
        String crewName = args[1];

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

        Scanner ordersInput = new Scanner(System.in);
        Crew crew = new Crew(crewName, ordersInput, systemManager);
        crew.run();

    }
}
