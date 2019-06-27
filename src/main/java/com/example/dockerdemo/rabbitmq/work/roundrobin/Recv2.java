package com.example.dockerdemo.rabbitmq.work.roundrobin;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Recv2 {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("[*] Waiting for messages. ");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("[*] Recv-2 received '" + msg + "'");
            try {
                doWork(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("[*] Done. ");
            }
        };

        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});
        //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//        channel.close();
//        connection.close();
    }

    private static void doWork(String msg) throws InterruptedException {
        for (char ch: msg.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}
