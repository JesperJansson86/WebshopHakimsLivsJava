package com.example.hakimlivs.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import org.springframework.amqp.core.FanoutExchange;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RabbitSend {

    ConnectionFactory connectionFactory;

    public static void main(String[] args) {
        RabbitSend send = new RabbitSend();
        send.test();
    }

    void test() {


    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
    RabbitTemplate rabbitTemplate = new RabbitTemplate();


    rabbitAdmin.declareExchange(new FanoutExchange("fanoutExchange"));
    // Skapa en queue
    rabbitAdmin.declareQueue(new Queue("mail"));
    // Skapa en binding
    rabbitAdmin.declareBinding(new Binding("mail", Binding.DestinationType.QUEUE, "fanoutExchange", "", Map.of()));
    // Produce message på exchange
    rabbitTemplate.convertAndSend("fanoutExchange", "", "Hej Hej");
    // Consume message på queue
    Message message = rabbitTemplate.receive("mail", 4000);

}
}
