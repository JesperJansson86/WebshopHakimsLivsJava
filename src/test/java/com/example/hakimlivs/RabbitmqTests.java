package com.example.hakimlivs;

import com.example.hakimlivs.rabbitmq.RabbitSend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = RabbitmqTests.RabbitmqTestsContextInitializer.class)
@AutoConfigureMockMvc(addFilters = false)
class RabbitmqTests {

    @Container
    private static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.9.5");

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RabbitSend rabbitSend;

    @Autowired
    ConnectionFactory connectionFactory;

    RabbitAdmin rabbitAdmin;

    @BeforeEach
    void setUp() {
        rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(new Queue("testMail"));
        rabbitAdmin.declareBinding(new Binding("testMail", Binding.DestinationType.QUEUE, "fanoutExchange", "123", null));

    }

    @Test
    void shouldSendToRabbitAndReceiveMessageBack() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rabbit/send")).andExpect(status().is2xxSuccessful());
        Message message1 = rabbitTemplate.receive("testMail", 4000);
        assertNotNull(message1);
        assertEquals("\"yay1\"", new String(message1.getBody()));
        assertTrue(message1.toString().contains("yay"));
    }
//
    @Test
    void shouldSendMailDtoAndReceiveBodyBack() {
        String expected = """
                {"mailTo":"test@test.com","type":"order"}""";
        rabbitSend.sendmail("test@test.com", "order");
        Message message1 = rabbitTemplate.receive("testMail", 4000);
        assertEquals("{\"mailTo\":\"test@test.com\",\"type\":\"order\"}", new String(message1.getBody()));
        assertEquals(expected, new String(message1.getBody()));

    }

    public static class RabbitmqTestsContextInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {


            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    configurableApplicationContext,
                    "spring.rabbitmq.host=" + rabbit.getContainerIpAddress(), "spring.rabbitmq.port=" + rabbit.getMappedPort(5672));

        }
    }
}