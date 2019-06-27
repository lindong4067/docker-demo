package com.example.dockerdemo.rabbitmq.topic;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

// topic : *.DEBUG
public class ReceiveLogsTopic1 {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "*.DEBUG"); //TODO topic e.g. OS.DEBUG  AP.DEBUG

        System.out.println(" [*] Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + msg + "'");
        };

        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});
    }
}
