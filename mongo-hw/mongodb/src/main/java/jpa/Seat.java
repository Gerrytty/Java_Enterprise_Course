package jpa;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seat {

    private String type;
    private Integer count;

}
