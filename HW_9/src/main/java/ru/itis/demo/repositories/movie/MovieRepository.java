package ru.itis.demo.repositories.movie;

import ru.itis.demo.model.Movie;
import ru.itis.demo.repositories.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findAllByUserId(Long userId);

}
