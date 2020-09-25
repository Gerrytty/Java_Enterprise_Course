import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private String queueName;

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static PdfCreator pdfCreator = new PdfCreator();

    public Consumer(String queueName) {
        this.queueName = queueName;
    }

    public void create() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                System.out.println(consumerTag);
                String m = new String(message.getBody());
                pdfCreator.create(queueName, m);

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
