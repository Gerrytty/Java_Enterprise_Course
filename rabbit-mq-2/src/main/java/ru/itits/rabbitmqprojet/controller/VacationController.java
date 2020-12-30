package ru.itits.rabbitmqprojet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.itits.rabbitmqprojet.model.Status;
import ru.itits.rabbitmqprojet.model.VacationDocument;
import ru.itits.rabbitmqprojet.model.VacationRequest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@RestController
public class VacationController {

    @Value("${my.host}")
    private String host;

    @Value("${queue.vacation.waits.name}")
    private String queueVacationWaitsName;

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public VacationController(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    @SneakyThrows
    @PostMapping("/vacation")
    public String vacation(@RequestBody VacationRequest vacationRequest) {

        VacationDocument vacationDocument = VacationDocument.builder()
                .employee(vacationRequest.getEmployee())
                .startDate(new SimpleDateFormat("dd/MM/yyyy").parse(vacationRequest.getStartDate()))
                .status(Status.WAIT)
                .build();

        String vacationDocumentJson = objectMapper.writeValueAsString(vacationDocument);

        System.out.println("Request send to queue for vacation: " + vacationDocumentJson);

        rabbitTemplate.convertAndSend(queueVacationWaitsName, vacationDocumentJson);
        rabbitTemplate.convertAndSend("info", "Request from " + vacationDocument.getEmployee()
        + "from " + LocalDateTime.now());

        return "ok";

    }

}
