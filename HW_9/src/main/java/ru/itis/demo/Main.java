package ru.itis.demo;

import ru.itis.demo.model.Movie;
import ru.itis.demo.model.NetflixUser;
import ru.itis.demo.repositories.movie.MovieRepository;
import ru.itis.demo.repositories.movie.MovieRepositoryImpl;
import ru.itis.demo.repositories.user.UserRepository;
import ru.itis.demo.repositories.user.UserRepositoryImpl;
import ru.itis.demo.util.ConnectionToDataBase;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieRepository movieRepository = new MovieRepositoryImpl(ConnectionToDataBase.getConnection());
        UserRepository userRepository = new UserRepositoryImpl(movieRepository);

//        NetflixUser user = NetflixUser.builder()
//                .email("yulu.2000@hotmail.com")
//                .firstName("Yuliya")
//                .lastName("Mihaylova")
//                .password("123").build();
//
//        System.out.println(user);
//
//        userRepository.save(user);

//        Movie movie = Movie.builder().
//                name("Interstellar").
//                producer("Nolan").
//                userId(1l).build();
//
//        movieRepository.save(movie);

        userRepository.findAll().forEach(System.out::println);

//        movieRepository.delete(3);

    }
}
