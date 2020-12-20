package ru.itis.mongohateoas.search.controller;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.mongohateoas.search.dto.AirplaneDto;
import ru.itis.mongohateoas.search.dto.AirplaneRequest;
import ru.itis.mongohateoas.search.repositories.AirplanesByRequestRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

    private final AirplanesByRequestRepository airplanesByRequestRepository;

    @Autowired
    public SearchController(AirplanesByRequestRepository airplanesByRequestRepository) {
        this.airplanesByRequestRepository = airplanesByRequestRepository;
    }

    @GetMapping("/airplanes/search")
    public ResponseEntity<List<AirplaneDto>> searchByRequest(AirplaneRequest airplaneRequest) {
        System.out.println("Search controller");
        return ResponseEntity.ok(airplanesByRequestRepository.findByRequest(airplaneRequest));
    }

}