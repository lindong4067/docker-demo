package com.example.dockerdemo.rabbitmq.topic;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        for (int i = 0; i <= 100; i++) {
            String[] topic1 = {"OS", "AP"};
            String[] topic2 = {"DEBUG", "INFO"};
            for (String t1 : topic1) {
                for (String t2 : topic2) {
                    String topic = t1 + "." + t2;
                    String message = topic + " - begin parsing " + i;
                    channel.basicPublish(EXCHANGE_NAME, topic, null, message.getBytes());
                    System.out.println(" [x] Sent '" + message + "'");
                    Thread.sleep(100);
                }
            }
        }
    }
}
