package ru.itits.rabbitmqprojet.consumer.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itits.rabbitmqprojet.model.VacationDocument;
import ru.itits.rabbitmqprojet.service.EmailSender;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMQListenerTopic {

    private final EmailSender emailSender;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQListenerTopic(EmailSender emailSender, ObjectMapper objectMapper) {
        this.emailSender = emailSender;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @RabbitListener(queues = "doc")
    public void processQueueVacationWaits(String message) {
        VacationDocument vacationDocument = objectMapper.readValue(message, VacationDocument.class);
        emailSender.send("mihaylova.yuliyaa@mail.ru", "You need to check request from " + vacationDocument.getEmployee());
    }

}
