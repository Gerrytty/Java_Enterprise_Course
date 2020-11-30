package ru.itis.mongohateoas.repositories;

import org.bson.types.ObjectId;

public interface AirRepository {
    void updateCount(ObjectId id, Integer newCount);
}
