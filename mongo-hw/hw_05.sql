use javalabdb

db.createCollection("airline");

db.airline.insert(
    {
        name: "S7",
        foundation_year: 1963
    }
    );

db.airline.insert(
    {
        name: "American Airlines Group",
        foundation_year: 1934
    }
    );

db.airplane.insert(
    {
        name: "Airbus A220",
        cost_mln_dollars: 76.5,
        owner_airline: ObjectId("5fc4090e06e9f02ec24763e1"),
        number_of_seats: 500,
        seats: [
            {
                type: "business_class",
                count: 200
            },
            {
                type: "economy_class",
                count: 300
            }
        ]
    }
    );

db.createCollection("airplane");  

db.airplane.insert(
    {
        name: "Boeing 717",
        cost_mln_dollars: 150,
        owner_airline: ObjectId("5fc4090e06e9f02ec24763e2"),
        number_of_seats: 800,
        seats: [
            {
                type: "business_class",
                count: 300
            },
            {
                type: "economy_class",
                count: 400
            },
            {
                type: "first_class",
                count: 100
            }
        ]
    }
    );

db.airplane.find();

db.airplane.find({name: 'Boeing 717'});

db.airplane.find({seats: {
        type: "business_class",
        count: 300
    }});

db.airplane.find({name: {$regex: /Boeing*/i}});

db.airplane.find({seats:{$elemMatch:{type : "business_class"}}});