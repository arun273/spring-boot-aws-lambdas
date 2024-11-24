package org.example.spring.lambda.case3;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

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
