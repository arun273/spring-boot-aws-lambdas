package org.example.spring.lambda.case4;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.net.URL;
import java.time.Duration;

public class DownloadUrlHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final S3Client s3Client = new S3Client();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String bucketName = "your-s3-bucket-name"; // Replace with your bucket name
        String fileName = request.getQueryStringParameters().get("fileName");

        if (fileName == null || fileName.isEmpty()) {
            return createResponse(400, "File name is required.");
        }

        URL downloadUrl = s3Client.generateDownloadPresignedUrl(bucketName, fileName, Duration.ofMinutes(15));
        return createResponse(200, downloadUrl.toString());
    }

    private APIGatewayProxyResponseEvent createResponse(int statusCode, String body) {
        return new APIGatewayProxyResponseEvent().withStatusCode(statusCode).withBody(body);
    }
}
