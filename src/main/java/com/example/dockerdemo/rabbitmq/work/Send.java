package com.example.dockerdemo.rabbitmq.work;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        boolean durable = true;//是否持久化
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        for (int i = 0; i < 100; i++) {
            String message =  "Hello World " + i + " ...";
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "' ");
            Thread.sleep(i * 10);
        }

//        channel.close();
//        connection.close();
    }
}
