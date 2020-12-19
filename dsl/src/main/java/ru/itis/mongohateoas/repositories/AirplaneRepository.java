package ru.itis.mongohateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.mongohateoas.models.Airplane;

@RepositoryRestResource
public interface AirplaneRepository extends MongoRepository<Airplane, String>, AirRepository {
}
