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
    @Autowired
    ConnectionFactory connectionFactory;

void test() {
    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory );
    RabbitTemplate rabbitTemplate = new RabbitTemplate();

    rabbitAdmin.declareExchange(new FanoutExchange("my-exchange-1"));
    // Skapa en queue
    rabbitAdmin.declareQueue(new Queue("for-test-only-1"));
    // Skapa en binding
    rabbitAdmin.declareBinding(new Binding("for-test-only-1", Binding.DestinationType.QUEUE, "my-exchange-1", "routing-key-is-not-used-for-fanout-but-required", Map.of()));
    // Produce message på exchange
    rabbitTemplate.convertAndSend("my-exchange-1", "", "Hej Hej");
    // Consume message på queue
    Message message = rabbitTemplate.receive("for-test-only-1", 4000);

}
}
