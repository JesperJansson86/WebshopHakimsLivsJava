package com.example.hakimlivs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.rabbitmq.client.ConnectionFactory;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@org.springframework.context.annotation.Configuration
@EnableScheduling
public class RabbitMqConfiguration {
RabbitAdmin rabbitAdmin;

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanoutExchange");
    }
    @Bean
    public Queue myQueue() {
        return new Queue("mail");
    }

    @Bean
    public Binding declareBindingGeneric() {
        return new Binding("mail",Binding.DestinationType.QUEUE,"fanoutExchange","", Map.of());
    }


    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("yhldjghk");
        connectionFactory.setPassword("dhFwExSP5Y3tObe-B_hUZNwldxNmoWEc");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("crow-01.rmq.cloudamqp.com");
        connectionFactory.setPort(1883);
        return connectionFactory;
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
