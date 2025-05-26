package com.example.task_api_backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiErrorMessage {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiErrorMessage(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
