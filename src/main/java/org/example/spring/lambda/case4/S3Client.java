package org.example.spring.lambda.case4;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Component
public class S3Client {

    private final S3Presigner s3Presigner;

    public S3Client() {
        this.s3Presigner = S3Presigner.builder()
                .region(Region.US_EAST_1) // Replace with your AWS region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public URL generateUploadPresignedUrl(String bucketName, String key, Duration expiration) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .putObjectRequest(putObjectRequest)
                .signatureDuration(expiration)
                .build();

        return s3Presigner.presignPutObject(presignRequest).url();
    }

    public URL generateDownloadPresignedUrl(String bucketName, String key, Duration expiration) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(expiration)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url();
    }
}
