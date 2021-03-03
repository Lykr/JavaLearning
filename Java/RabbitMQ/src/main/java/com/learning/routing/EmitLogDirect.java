package com.learning.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";
    private static int num = 0;

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            while (true) {
                String message = "Send " + ++num + " message.";
                Scanner scanner = new Scanner(System.in);
                String severity = scanner.next();
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8")); // send a message to exchange, whose routing key is severity
                System.out.println(" [x] Sent '" + message + "'");
                Thread.sleep(500);
            }
        }
    }
}
