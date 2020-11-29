package driver;

import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Projections.*;

public class Main {
    public static void main(String[] args) {

        // MongoClient mongo = new MongoClient("localhost", 27017);

        MongoClient mongo = MongoClients.create();

        MongoDatabase db = mongo.getDatabase("javalabdb");

        MongoCollection<Document> collection = db.getCollection("airplane");

        collection.find().forEach(document -> System.out.println(document.getString("name")));

        Document searchQuery = new Document();

        FindIterable<Document> resultDocuments =
                collection.find(searchQuery)
                .projection(fields(include("cost_mln_dollars", "name", "seats"), excludeId()));

        resultDocuments.forEach(document -> System.out.println(document.toJson()));

    }
}
