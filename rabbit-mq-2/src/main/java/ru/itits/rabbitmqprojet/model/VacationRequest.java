package ru.itits.rabbitmqprojet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationRequest {
    private Employee employee;
    private String startDate;
    private String email;
}
