package ru.itis.mongohateoas.search.repositories;
import ru.itis.mongohateoas.search.dto.AirplaneDto;
import ru.itis.mongohateoas.search.dto.AirplaneRequest;

import java.util.List;

public interface AirplanesByRequestRepository {
    List<AirplaneDto> findByRequest(AirplaneRequest userRequest);
}