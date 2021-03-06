package com.example.dockerdemo.rabbitmq.publishsubscribe;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ReceiveLog1 {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + msg + "'");
        };

        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});
    }
}
