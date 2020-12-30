package ru.itits.rabbitmqprojet.consumer.direct;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.itits.rabbitmqprojet.service.Vars;

import java.io.File;
import java.io.PrintWriter;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMQListenerDirect {

    @SneakyThrows
    @RabbitListener(queues = "vacation_docs")
    public void processQueueVacationWaits(String message) {
        File file = new File(Vars.PATH_DOC + "log.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.println(message);
        pw.flush();
        pw.close();
    }

}
