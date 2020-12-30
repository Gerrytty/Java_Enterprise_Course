package ru.itits.rabbitmqprojet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itits.rabbitmqprojet.model.PrizeRequest;

@RestController
public class PrizeController {

    @Value("${my.host}")
    private String host;

    @Value("${queue.vacation.waits.name}")
    private String queueVacationWaitsName;

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PrizeController(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    @SneakyThrows
    @PostMapping("/prize")
    public String prize(@RequestBody PrizeRequest prizeRequest) {
        String json = objectMapper.writeValueAsString(prizeRequest);
        rabbitTemplate.convertAndSend("prize", json);
        return "ok";

    }

}
