package ru.itis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.demo.model.NetflixUser;
import ru.itis.demo.repositories.movie.MovieRepository;
import ru.itis.demo.repositories.movie.MovieRepositoryImpl;
import ru.itis.demo.repositories.user.UserRepository;
import ru.itis.demo.repositories.user.UserRepositoryImpl;
import ru.itis.demo.util.ConnectionToDataBase;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
//
//        MovieRepository movieRepository = new MovieRepositoryImpl(ConnectionToDataBase.getConnection());
//        UserRepository userRepository = new UserRepositoryImpl(movieRepository);
//        userRepository.findAll().forEach(System.out::println);
//
//        NetflixUser user = new NetflixUser();
//        user.builder()
//                .email("yulu.2000@hotmail.com")
//                .firstName("Yuliya")
//                .lastName("Mihaylova")
//                .password("123");
//
//        System.out.println(user);

    }

}
