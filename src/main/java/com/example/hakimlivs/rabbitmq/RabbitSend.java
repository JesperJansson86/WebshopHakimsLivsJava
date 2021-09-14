package com.example.hakimlivs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import org.springframework.amqp.core.FanoutExchange;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping(path = ("/api/rabbit"))
public class RabbitSend {
    RabbitTemplate rabbitTemplate;
    public RabbitSend(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/send")
    public void send() {
    String message = "yay" + RabbitConsume.counter;
    RabbitConsume.counter++;
        rabbitTemplate.convertAndSend("fanoutExchange", "",message );
    }

    public void sendmail(String mailTo, String type) {
        MailDTO mailDTO = new MailDTO(mailTo,type);
        rabbitTemplate.convertAndSend("fanoutExchange", "",mailDTO );
    }
}
