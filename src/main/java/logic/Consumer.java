package logic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;


public class Consumer {

//    private static final URI uri = URI.create("amqp://bank-provider-proxy-consumer:quee7vahSh@services-stage.elegro.eu:5672/bank-provider-proxy");
    private static String QUEUE_NAME = "jobs";


    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUri(uri);
        factory.setUsername("bank-provider-proxy-consumer");
        factory.setPassword("quee7vahSh");
        factory.setHost("services-stage.elegro.eu");
        factory.setPort(5672);
        factory.setVirtualHost("bank-provider-proxy");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicConsume(QUEUE_NAME, true, (consumerTag, message) -> {
            String m = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Message from queue: " + m);
        }, consumerTag -> {});
    }
}