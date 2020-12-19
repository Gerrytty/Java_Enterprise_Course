package ru.itis.mongohateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.itis.mongohateoas.models.Airplane;
import ru.itis.mongohateoas.service.AirplaneService;

@RepositoryRestController
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @RequestMapping(value = "airplanes", method = RequestMethod.POST)
    public ResponseEntity<?> saveAirplane(@RequestBody EntityModel<Airplane> airplane) {
        airplaneService.save(airplane.getContent());
        return ResponseEntity.ok(EntityModel.of(airplane));
    }

}
