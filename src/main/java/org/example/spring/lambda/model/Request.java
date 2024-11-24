package org.example.spring.lambda.model;

public class Request {
    private String operation;
    private String id;
    private String data;
    private String fileName;
    private String fileContent;


    public Request() {
    }

    public Request(String operation, String id, String data, String fileName, String fileContent) {
        this.operation = operation;
        this.id = id;
        this.data = data;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
