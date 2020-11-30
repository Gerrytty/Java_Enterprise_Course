package jpa;

import model.Airline;
import model.Airplane;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        AirlineRepository airlineRepository = context.getBean(AirlineRepository.class);
        AirplaneRepository airplaneRepository = context.getBean(AirplaneRepository.class);

        Airline airline = Airline.builder()
                .name("United Continental 2")
                .foundation_year(1972)
                .build();

        airlineRepository.save(airline);

        Airplane airplane = Airplane.builder()
                .cost_mln_dollars(80f)
                .number_of_seats(400)
                .owner_airline(new ObjectId("5fc4090e06e9f02ec24763e1"))
                .seats(asList(Seat.builder().count(100).type("business_class").build()))
                .build();

        airplaneRepository.save(airplane);

        Airline airlineA = airlineRepository.findById("5fc4090e06e9f02ec24763e1").orElseThrow(IllegalArgumentException::new);

        System.out.println(airlineA);

        airlineRepository.find("S7").forEach(System.out::println);
    }

}
