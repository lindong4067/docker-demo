package com.example.dockerdemo.rabbitmq.publishsubscribe;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for (int i = 0; i <= 100; i++) {
            String message = " DEBUG - begin parsing " + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(100);
        }
    }
}
