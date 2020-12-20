package ru.itis.mongohateoas.search.repositories;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import ru.itis.mongohateoas.models.Airplane;
import ru.itis.mongohateoas.repositories.AirplaneRepository;
import ru.itis.mongohateoas.search.dto.AirplaneDto;
import ru.itis.mongohateoas.search.dto.AirplaneRequest;

import static ru.itis.mongohateoas.models.QAirplane.airplane;

@Repository
public class AirplanesByRequestRepositoryImpl implements AirplanesByRequestRepository {

    private final AirplaneRepository airplaneRepository;

    @Autowired
    public AirplanesByRequestRepositoryImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public List<AirplaneDto> findByRequest(AirplaneRequest airplaneRequest) {

        BooleanBuilder predicate = new BooleanBuilder();

        if (airplaneRequest.getCost_mln_dollars() != null) {
            predicate.or(airplane.cost_mln_dollars.eq(airplaneRequest.getCost_mln_dollars()));
        }

        if (airplaneRequest.getNumber_of_seats() != null) {
            predicate.or(airplane.number_of_seats.eq(airplaneRequest.getNumber_of_seats()));
        }

        List<Airplane> airplanes = (List<Airplane>) airplaneRepository.findAll(predicate);

        return airplanes.stream().map(row -> AirplaneDto.builder()
                .cost_mln_dollars(row.getCost_mln_dollars())
                .number_of_seats(row.getNumber_of_seats())
                .build()).collect(Collectors.toList());
    }

}
