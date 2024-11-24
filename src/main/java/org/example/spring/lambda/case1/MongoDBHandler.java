package org.example.spring.lambda.case1;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.bson.Document;

public class MongoDBHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final MongoDBClient mongoDBClient = new MongoDBClient(null); // Inject MongoTemplate in a production setup.

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String operation = request.getQueryStringParameters().get("operation");
        String collection = "mycollection";

        if ("save".equalsIgnoreCase(operation)) {
            Document document = Document.parse(request.getBody());
            mongoDBClient.save(collection, document);
            return createResponse(200, "Data saved successfully");
        } else if ("fetch".equalsIgnoreCase(operation)) {
            String id = request.getQueryStringParameters().get("id");
            Document result = mongoDBClient.fetch(collection, id);
            return createResponse(200, result != null ? result.toJson() : "No data found");
        }

        return createResponse(400, "Invalid operation");
    }

    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        return new APIGatewayProxyResponseEvent().withStatusCode(statusCode).withBody(body);
    }
}
