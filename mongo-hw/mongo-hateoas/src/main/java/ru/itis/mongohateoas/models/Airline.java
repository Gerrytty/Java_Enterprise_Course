package ru.itis.mongohateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "airline")
public class Airline {

    @Id
    private String _id;
    private String name;
    private Integer foundation_year;
    private Integer count_of_airplanes;

}
