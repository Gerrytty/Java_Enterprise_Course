package ru.itis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetflixUser {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private List<Movie> watchedMovies;

    public NetflixUser(Long id, String firstName, String lastName, String email, String password, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
}
