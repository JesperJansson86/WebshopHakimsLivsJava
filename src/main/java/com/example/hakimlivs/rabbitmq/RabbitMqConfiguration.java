package com.example.hakimlivs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;

@Configuration
@EnableScheduling
public class RabbitMqConfiguration {

//    @Bean
//    public RabbitSend rabbitSend(RabbitTemplate template) {
//        return new RabbitSend(template);}
//    @Bean
//    public RabbitConsume rabbitConsume(RabbitTemplate template){
//        return new RabbitConsume(template);
//    }


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
        return new Binding("mail",Binding.DestinationType.QUEUE,"fanoutExchange","2143124", Map.of());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, final Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }



}
