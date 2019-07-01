package com.example.dockerdemo.rabbitmq.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeoutException;

public class RPCClient implements AutoCloseable {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.159.129");
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] args) {
        try(RPCClient client = new RPCClient()) {
            for (int i = 0; i < 32; i++) {
                String str_i = Integer.toString(i);
                System.out.println(" [x] Requesting fib(" + str_i + ")");
                String response = client.call(str_i);
                System.out.println(" [.] Got '" + response + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String call(String message) throws IOException, InterruptedException {
        String corrId = UUID.randomUUID().toString();
        String replyQueue = channel.queueDeclare().getQueue();

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueue)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes());

        ArrayBlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueue, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody()));
            }
        }, (consumerTag, sig) -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
