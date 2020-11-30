package ru.itis.mongohateoas.processor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.mongohateoas.controller.AirplaneController;
import ru.itis.mongohateoas.models.Airplane;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AirplaneProcessor implements RepresentationModelProcessor<EntityModel<Airplane>> {

    @Override
    public EntityModel<Airplane> process(EntityModel<Airplane> model) {
        Airplane airplane = model.getContent();

        if (airplane != null) {
            model.add(linkTo(methodOn(AirplaneController.class)
                    .saveAirplane(model)).withRel("/airplanes/"));
        }

        return model;
    }

}
