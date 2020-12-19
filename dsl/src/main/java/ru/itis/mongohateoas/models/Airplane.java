package ru.itis.mongohateoas.models;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@QueryEntity
@Document(collection = "airplane")
public class Airplane {

    @Id
    private String _id;
    private Float cost_mln_dollars;

    @BsonProperty("_id")
    @BsonId
    private ObjectId owner_airline;

    private Integer number_of_seats;
    private List<Seat> seats;

}
