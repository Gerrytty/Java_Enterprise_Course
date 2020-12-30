package ru.itits.rabbitmqprojet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${my.host}")
    private String host;

    @Value("${queue.vacation.waits.name}")
    private String queueVacationWaitsName;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue vacationWaitsConfirm() {
        return new Queue(queueVacationWaitsName);
    }

    @Bean
    public Queue prize() {
        return new Queue("prize_doc");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(queueVacationWaitsName);
    }

    @Bean
    public Binding infoBinding() {
        return BindingBuilder.bind(vacationWaitsConfirm()).to(directExchange()).with("info");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic-q");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(vacationWaitsConfirm()).to(topicExchange()).with("*doc*");
    }

}
