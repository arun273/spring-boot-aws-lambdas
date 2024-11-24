package org.example.spring.lambda.case1;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.bson.Document;

@Component
public class MongoDBClient {
    private final MongoTemplate mongoTemplate;

    public MongoDBClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(String collectionName, Document document) {
        mongoTemplate.save(document, collectionName);
    }

    public Document fetch(String collectionName, String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Document.class, collectionName);
    }
}
