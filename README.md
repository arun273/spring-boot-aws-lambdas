**Deployment of package: org.example.spring.lambda.case1**

**Build the JAR**

mvn clean package

**Upload to AWS Lambda** **Create two Lambda functions**.

MongoDB Function: **org.example.spring.lambda.case1.MongoDBHandler**

S3 Function: **org.example.spring.lambda.case1.S3Handler**

**Set up & Create two routes in API Gateway, each pointing to one of the Lambda functions**

/mongodb → MongoDBHandler

/s3 → S3Handler

**Example Requests**

**Save Data to MongoDB**

curl -X POST https://api-gateway-endpoint.com/mongodb?operation=save -d '{"_id":"1", "name":"Test"}'

**Fetch Data from MongoDB**

curl -X GET https://api-gateway-endpoint.com/mongodb?operation=fetch&id=1

**Upload File to S3**

curl -X POST https://api-gateway-endpoint.com/s3?operation=upload&fileName=test.pdf -d '<base64-file-content>'

**Download File from S3**

curl -X GET https://api-gateway-endpoint.com/s3?operation=download&fileName=test.pdf

**Note**

This design ensures that each Lambda function operates independently and handles distinct responsibilities while being deployed from the same JAR.

**-------------------------------------------------------------------------------------------------------------**

**Deployment of package: org.example.spring.lambda.case2**

**Create two separate Lambda functions in AWS**

Lambda Function 1: **org.example.spring.lambda.case2.LambdaOneHandler**

Lambda Function 2: **org.example.spring.lambda.case2.LambdaTwoHandler**

**Configure API Gateway with separate routes in AWS API Gateway to target the respective Lambda functions**

/operation1 → Lambda Function 1

/operation2 → Lambda Function 2

**Example Requests**

**Trigger Lambda Function 1  and Trigger Lambda Function 2**

curl -X POST https://api-gateway-endpoint.com/operation1?operation=fetchData

curl -X POST https://api-gateway-endpoint.com/operation2?operation=uploadFile

**Note**

This approach allows each Lambda function to be independently targeted by AWS API Gateway without relying on Spring context or path-based routing.

**-------------------------------------------------------------------------------------------------------------**

**Deployment of package: org.example.spring.lambda.case3**

**Deploy to AWS Lambda & Upload the same JAR file for each Lambda function**

Lambda Function 1 (MongoDB Operations): **Handler: dataLambdaHandler**

Lambda Function 2 (S3 Operations):  **Handler: fileLambdaHandler**

**API Gateway & Create endpoints in API Gateway and map them to respective Lambda functions:**

/data → MongoDB Lambda Function  &  /file → S3 Lambda Function

**Test**

**Save Data to MongoDB & Fetch Data from MongoDB**

curl -X POST https://api-gateway-endpoint.com/data -d '{"operation":"save", "data":"{\"_id\":\"1\", \"name\":\"Test\"}"}'

curl -X POST https://api-gateway-endpoint.com/file -d '{"operation":"upload", "fileName":"test.pdf", "fileContent":"<base64-content>"}'


**Upload File to S3 & Download File from S3**

curl -X POST https://api-gateway-endpoint.com/file -d '{"operation":"upload", "fileName":"test.pdf", "fileContent":"<base64-content>"}'

curl -X POST https://api-gateway-endpoint.com/file -d '{"operation":"download", "fileName":"test.pdf"}'

**Note**

This implementation provides a clean, modular, and efficient way to handle the described use case.

**-------------------------------------------------------------------------------------------------------------**

**Deployment of package: org.example.spring.lambda.case4**

mvn clean package

Upload to AWS Lambda:

**Create two Lambda functions**

Upload URL Function: **org.example.spring.lambda.case4.UploadUrlHandler**

Download URL Function: **org.example.spring.lambda.case4.DownloadUrlHandler**

**Set up API Gateway &  Create two routes in API Gateway, each pointing to one of the Lambda functions**

/upload-url → UploadUrlHandler

/download-url → DownloadUrlHandler


**Example Requests -  Generate Upload Pre-Signed URL & Generate Download Pre-Signed UR**

curl -X GET "https://api-gateway-endpoint.com/upload-url?fileName=test.pdf"

curl -X GET "https://api-gateway-endpoint.com/download-url?fileName=test.pdf"

**Note**

This setup ensures that the Lambda functions are independently targeted by API Gateway and perform their respective tasks (upload/download URL generation) without relying on path-based routing or Spring context.


