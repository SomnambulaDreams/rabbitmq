package logic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Sender {

    private static String QUEUE_NAME = "Hello";


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost();
//        factory.setUri();

        try (Connection connection = factory.newConnection();) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = "I did it!" ;

            channel.basicPublish("", QUEUE_NAME, false, null, message.getBytes());
            System.out.println("Message has benn sent!");
        }
    }
}