package ru.itis.mongohateoas.search.dto;

import lombok.Data;

@Data
public class AirplaneRequest {
    private Float cost_mln_dollars;
    private Float number_of_seats;
}
