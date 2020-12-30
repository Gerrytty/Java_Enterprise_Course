package ru.itits.rabbitmqprojet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VacationDocument {

    private Employee employee;
    private Date startDate;
    private Status status;

}
