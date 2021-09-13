package com.example.hakimlivs.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("/api/rabbit"))
public class RabbitConsume {
    static int counter = 1;
    RabbitTemplate rabbitTemplate;
    public RabbitConsume(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @RabbitListener(queues="mail")
    @GetMapping("/consume")
    public String receive(){
        Message message1 = rabbitTemplate.receive("mail",4000);

        System.out.println(message1.getBody());

        return new String (message1.getBody());
    }
}
