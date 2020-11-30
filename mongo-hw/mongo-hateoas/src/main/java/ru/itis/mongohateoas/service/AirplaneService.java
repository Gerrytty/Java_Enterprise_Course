package ru.itis.mongohateoas.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mongohateoas.models.Airline;
import ru.itis.mongohateoas.models.Airplane;
import ru.itis.mongohateoas.repositories.AirlineRepository;
import ru.itis.mongohateoas.repositories.AirplaneRepository;

@Service
public class AirplaneService {

    private final AirlineRepository airlineRepository;
    private final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplaneService(AirlineRepository airlineRepository, AirplaneRepository airplaneRepository) {
        this.airlineRepository = airlineRepository;
        this.airplaneRepository = airplaneRepository;
    }

    public void save(Airplane airplane) {
        ObjectId ownerId = airplane.getOwner_airline();
        System.out.println("Owner id = " + ownerId.toString());

        Airline airline = airlineRepository.findById(ownerId.toString()).get();
        Integer count = airline.getCount_of_airplanes();

        if (count == null) {
            count = 1;
        }
        else count++;

        airplaneRepository.updateCount(ownerId, count);
        airplaneRepository.save(airplane);
    }

}
