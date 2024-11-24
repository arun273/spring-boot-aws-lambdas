package org.example.spring.lambda.case2;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class LambdaOneHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String operation = request.getQueryStringParameters().get("operation");

        if ("fetchData".equalsIgnoreCase(operation)) {
            // Example logic
            return createResponse(200, "Data fetched successfully!");
        } else if ("saveData".equalsIgnoreCase(operation)) {
            // Example logic
            return createResponse(200, "Data saved successfully!");
        }

        return createResponse(400, "Invalid operation.");
    }

    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(body);
    }
}
