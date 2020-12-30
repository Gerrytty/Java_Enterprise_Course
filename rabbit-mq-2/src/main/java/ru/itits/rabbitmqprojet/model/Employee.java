package ru.itits.rabbitmqprojet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String firstName;
    private String secondName;
    private String lastName;
    private String passportNumber;
    private String phone;
    private String position;
    private Integer salary;

}
