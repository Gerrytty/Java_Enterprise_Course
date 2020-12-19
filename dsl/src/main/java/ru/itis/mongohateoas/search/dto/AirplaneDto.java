package ru.itis.mongohateoas.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirplaneDto {
    private float cost_mln_dollars;
    private int number_of_seats;
}
