package ru.itits.rabbitmqprojet.consumer.fanout;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itits.rabbitmqprojet.model.VacationDocument;
import ru.itits.rabbitmqprojet.service.VacationDocCreate;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMqListener {

    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMqListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        System.out.println("Received from queue 1: " + message);
    }

    @SneakyThrows
    @RabbitListener(queues = "vacation_docs")
    public void processQueueVacationWaits(String message) {
        System.out.println("Received from vacation_waits_confirm: " + message);
        VacationDocCreate vacationDocCreate = new VacationDocCreate();
        VacationDocument vacationDocument = objectMapper.readValue(message, VacationDocument.class);
        vacationDocCreate.createVacationDocument(vacationDocument);
    }

}
