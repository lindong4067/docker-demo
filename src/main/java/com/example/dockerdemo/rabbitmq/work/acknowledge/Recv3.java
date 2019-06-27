package com.example.dockerdemo.rabbitmq.work.acknowledge;

import com.example.dockerdemo.rabbitmq.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Recv3 {
    private static final String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ChannelUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        boolean durable = true;//是否持久化
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        //Using message acknowledgments and prefetchCount you can set up a work queue.
        channel.basicQos(1);

        System.out.println("[*] Waiting for messages. ");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("[*] Recv-3 received '" + msg + "'");
            try {
                doWork(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("[*] Done. ");
                //TODO use ack
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }
        };

        boolean autoAck = false; //TODO use ack
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});
        //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//        channel.close();
//        connection.close();
    }

    private static void doWork(String msg) throws InterruptedException {
        for (char ch: msg.toCharArray()) {
            if (ch == '.') Thread.sleep(100);
        }
    }
}
