package ru.itis.mongohateoas.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class AirRepositoryImpl implements AirRepository {

    @Override
    public void updateCount(ObjectId id, Integer newCount) {

        MongoClient client = MongoClients.create();
        MongoDatabase db = client.getDatabase("javalabdb");
        MongoCollection<Document> collection = db.getCollection("airline");

        Document query = new Document();
        query.append("_id", id);

        Document setData = new Document();
        setData.append("count_of_airplanes", newCount);

        Document update = new Document();
        update.append("$set", setData);
        collection.updateOne(query, update);

    }
}
