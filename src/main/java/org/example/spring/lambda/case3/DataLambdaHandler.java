package org.example.spring.lambda.case3;

import org.bson.Document;
import org.example.spring.lambda.model.Request;
import org.example.spring.lambda.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("dataLambdaHandler")
public class DataLambdaHandler implements Function<Request, Response> {

    @Autowired
    private MongoDBClient mongoDBClient;

    @Override
    public Response apply(Request request) {
        String operation = request.getOperation();
        String collection = "mycollection";

        if ("save".equalsIgnoreCase(operation)) {
            Document document = Document.parse(request.getData());
            mongoDBClient.save(collection, document);
            return new Response("Data saved successfully");
        } else if ("fetch".equalsIgnoreCase(operation)) {
            Document result = mongoDBClient.fetch(collection, request.getId());
            return new Response("Fetched Data: " + result.toJson());
        }

        return new Response("Unsupported operation");
    }
}
