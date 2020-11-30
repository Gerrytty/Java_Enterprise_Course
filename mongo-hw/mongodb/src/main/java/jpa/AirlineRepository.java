package jpa;

import model.Airline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirlineRepository extends MongoRepository<Airline, String> {
    @Query(value = "{'name': ?0}")
    List<Airline> find(@Param("name") String name);
}