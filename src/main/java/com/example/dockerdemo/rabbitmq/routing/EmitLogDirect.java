package com.example.dockerdemo.rabbitmq.routing;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        for (int i = 0; i <= 100; i++) {
            String logLevel = i % 2 == 0 ? "DEBUG" : "INFO";
            String message = logLevel + " - begin parsing " + i;
            channel.basicPublish(EXCHANGE_NAME, logLevel, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(100);
        }
    }
}
