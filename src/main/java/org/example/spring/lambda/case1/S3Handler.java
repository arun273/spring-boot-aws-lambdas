package org.example.spring.lambda.case1;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.io.InputStream;
import java.util.Base64;

public class S3Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final AWSS3Client s3Client = new AWSS3Client();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String operation = request.getQueryStringParameters().get("operation");
        String bucketName = "my-bucket";
        String fileName = request.getQueryStringParameters().get("fileName");

        if ("upload".equalsIgnoreCase(operation)) {
            byte[] content = Base64.getDecoder().decode(request.getBody());
            s3Client.uploadFile(bucketName, fileName, content);
            return createResponse(200, "File uploaded successfully");
        } else if ("download".equalsIgnoreCase(operation)) {
            InputStream fileStream = s3Client.downloadFile(bucketName, fileName);
            return createResponse(200, "File downloaded successfully");
        }

        return createResponse(400, "Invalid operation");
    }

    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        return new APIGatewayProxyResponseEvent().withStatusCode(statusCode).withBody(body);
    }
}
