package org.example.spring.lambda.case5;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.example.spring.lambda.LambdaApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;

import java.util.Base64;

public class UploadHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final S3Service s3Service;

    public UploadHandler() {
        var context = FunctionalSpringApplication.run(LambdaApplication.class);
        this.s3Service = context.getBean(S3Service.class);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        try {
            String fileKey = request.getQueryStringParameters().get("key");
            byte[] fileContent = Base64.getDecoder().decode(request.getBody());

            s3Service.uploadPdf(fileKey, fileContent);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody("File uploaded successfully.");
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody("Error uploading file.");
        }
    }
}
