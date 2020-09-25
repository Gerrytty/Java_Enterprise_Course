import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_1 = "dismiss";
    private final static String QUEUE_2 = "recruit";

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) throws JsonProcessingException {

        // создаем фабрику подключений
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // говорим, куда подключаться
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            while (true) {
                UserInfo userInfo = readInfo();

                String json = objectMapper.writeValueAsString(userInfo);

                // создаем канал
                Channel channel = connection.createChannel();
                channel.basicPublish("", QUEUE_1, null, json.getBytes());
                channel.basicPublish("", QUEUE_2, null, json.getBytes());
            }
        } catch (TimeoutException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static UserInfo readInfo() {

        return new UserInfo(read("Введите ваше имя: "),
                read("Введите вашу фамлию: "),
                read("Введите дату выдачи паспорта: "),
                read("Введите ваш возраст: "),
                read("Ведите номер паспорта: "));

    }

    private static String read(String out) {
        String s = "";
        System.out.print(out);
        try {
            s = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return s;
    }

}