package org.example.spring.lambda.case3;

import org.example.spring.lambda.model.Request;
import org.example.spring.lambda.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Base64;
import java.util.function.Function;

@Component("fileLambdaHandler")
public class FileLambdaHandler implements Function<Request, Response> {

    @Autowired
    private AWSS3Client s3Client;

    @Override
    public Response apply(Request request) {
        String operation = request.getOperation();
        String bucketName = "my-bucket";
        String key = request.getFileName();

        if ("upload".equalsIgnoreCase(operation)) {
            byte[] fileContent = Base64.getDecoder().decode(request.getFileContent());
            s3Client.uploadFile(bucketName, key, fileContent);
            return new Response("File uploaded successfully");
        } else if ("download".equalsIgnoreCase(operation)) {
            InputStream fileStream = s3Client.downloadFile(bucketName, key);
            return new Response("File downloaded successfully");
        }

        return new Response("Unsupported operation");
    }
}
