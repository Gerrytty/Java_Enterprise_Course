package ru.itis.mongohateoas.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seat {

    private String type;
    private Integer count;

}
