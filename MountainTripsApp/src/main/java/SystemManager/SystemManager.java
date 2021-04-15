package SystemManager;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SystemManager {

    private static final String ordersExchangeName = "ORDERS_EXCHANGE";
    private static final String acknowledgementsExchangeName = "ACKNOWLEDGEMENTS_EXCHANGE";
    private static final String adminExchangeName = "ADMIN_EXCHANGE";

    private Channel channel;

    public SystemManager(Channel channel){
        this.channel = channel;
    }

    public void initSystem() throws IOException {
        this.channel.exchangeDeclare(SystemManager.ordersExchangeName, BuiltinExchangeType.TOPIC);
        this.channel.exchangeDeclare(SystemManager.acknowledgementsExchangeName, BuiltinExchangeType.TOPIC);
        this.channel.exchangeDeclare(SystemManager.adminExchangeName, BuiltinExchangeType.TOPIC);
    }

    public void initSystemQueue(String systemName) throws IOException {
        String routingKey = "#";
        this.channel.queueDeclare(systemName, true, false, false, null);
        this.channel.queueBind(systemName, SystemManager.acknowledgementsExchangeName, routingKey);
        this.channel.queueBind(systemName, SystemManager.ordersExchangeName, routingKey);
    }

    public void initOrdersQueue(String orderType) throws IOException {
        this.channel.queueDeclare(orderType, true, false, false, null);
        this.channel.queueBind(orderType, SystemManager.ordersExchangeName, orderType);
    }

    public String initAcknowledgementsQueue(String crewName) throws IOException {
        String ackQueueName = this.channel.queueDeclare().getQueue();
        String routingKey =  crewName + ".*";
        this.channel.queueBind(ackQueueName, SystemManager.acknowledgementsExchangeName, routingKey);
        return ackQueueName;
    }

    public String initAdminQueue(boolean isSupplier) throws IOException {
        String adminQueueTag = this.channel.queueDeclare().getQueue();
        if(isSupplier) {
            this.channel.queueBind(adminQueueTag, SystemManager.adminExchangeName, "*.supplier");
        }
        else{
            this.channel.queueBind(adminQueueTag, SystemManager.adminExchangeName, "crew.*");
        }
        return adminQueueTag;
    }

    public void acceptOrders(String orderType, String supplierName, Random random) throws IOException{
        this.channel.basicConsume(orderType, false, "", new DefaultConsumer(this.channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String crewName = new String(body, StandardCharsets.UTF_8);
                int acknowledgementID = random.nextInt();
                System.out.println(
                        "Supplier "+ supplierName + " received order for " + orderType + " from crew " + crewName
                );
                postAcknowledgement(acknowledgementID, crewName, orderType, supplierName);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

    public void acceptAcknowledgements(String acknowledgementsTag, String crewName) throws IOException {
        this.channel.basicConsume(acknowledgementsTag, false, "", new DefaultConsumer(this.channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                int acknowledgementID = Integer.parseInt(envelope.getRoutingKey().split("\\.")[1]);

                String[] message = new String(body, StandardCharsets.UTF_8).split("\\.");
                String supplierName = message[0];
                String orderType = message[1];

                System.out.println(
                        "Crew " + crewName + " received acknowledgement of ID " + acknowledgementID +
                                " from supplier " + supplierName + " for product " + orderType
                );

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

    public void acceptSystemMessages(String systemName) throws IOException {
        this.channel.basicConsume(systemName, false, "", new DefaultConsumer(this.channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                String routingKey = envelope.getRoutingKey();
                System.out.println(
                        "Admin of system " + systemName + " logged message " + message + " of routnig key " + routingKey
                );
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

    public void acceptAdminMessages(String adminCommunicationTag, String name) throws IOException {
        this.channel.basicConsume(adminCommunicationTag, false, "", new DefaultConsumer(this.channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println(
                       name + " received message from admin: " + message
                );
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

    public void postOrder(String orderType, String crewName) throws IOException {
        this.channel.basicPublish(SystemManager.ordersExchangeName, orderType, null, crewName.getBytes());
    }

    public void postAcknowledgement(int acknowledgementID, String crewName, String orderType, String supplierName) throws IOException {
        String ackKey = crewName + "." + acknowledgementID;
        byte[] message = (supplierName + "." + orderType).getBytes();
        this.channel.basicPublish(SystemManager.acknowledgementsExchangeName, ackKey, null, message);
    }

    public void postAdminMessage(String message, boolean toSuppliers, boolean toCrews) throws IOException {
        String routingKey;
        if(toCrews && toSuppliers){
            routingKey = "crew.supplier";
        }
        else if(toCrews){
            routingKey = "crew.none";
        }
        else{
            routingKey = "none.supplier";
        }
        this.channel.basicPublish(SystemManager.adminExchangeName, routingKey, null, message.getBytes());
    }
}
