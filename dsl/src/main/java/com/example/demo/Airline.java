package com.example.demo;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@QueryEntity
@Document(collection = "airline")
public class Airline {

    @Id
    private String _id;
    private String name;
    private Integer foundation_year;
    private Integer count_of_airplanes;

}
