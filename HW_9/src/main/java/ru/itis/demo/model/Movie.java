package ru.itis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    private Long id;

    private String name;
    private String producer;
    private Date yearOfIssue;
    private Long userId;

}
