package org.example.spring.lambda.case2;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class LambdaTwoHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String operation = request.getQueryStringParameters().get("operation");

        if ("uploadFile".equalsIgnoreCase(operation)) {
            // Example logic for file upload
            return createResponse(200, "File uploaded successfully!");
        } else if ("downloadFile".equalsIgnoreCase(operation)) {
            // Example logic for file download
            return createResponse(200, "File downloaded successfully!");
        }

        return createResponse(400, "Invalid operation.");
    }

    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(body);
    }
}
