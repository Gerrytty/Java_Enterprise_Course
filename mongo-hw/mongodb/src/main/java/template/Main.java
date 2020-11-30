package template;

import model.Airline;
import model.Airplane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

        Airline airline = Airline.builder().name("New airline").foundation_year(2020).build();
        template.save(airline);

        List<Airplane> airplanes = template.find(new Query(where("name").is("Airbus A220")), Airplane.class, "airplane");

        airplanes.forEach(System.out::println);
    }
}
