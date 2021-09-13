package com.example.hakimlivs.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.web.bind.annotation.PostMapping;

public class RabbitNyTest {
    @PostMapping(path = "/rabbitSend")
    public void send() throws Exception {
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqp://guest:guest@localhost";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        factory.setPort(5672);
        factory.setRequestedHeartbeat(30);
        factory.setConnectionTimeout(30);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello", false, false, false, null);
        String message = "Hello CloudAMQP!";
        channel.basicPublish("", "hello", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume("hello", true, consumer);

//        while (true) {
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            message = new String(delivery.getBody());
//            System.out.println(" [x] Received '" + message + "'");
//        }
    }


}


