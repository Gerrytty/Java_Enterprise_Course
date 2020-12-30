package ru.itits.rabbitmqprojet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeRequest {
    private Employee employee;
    private int sum;
}
