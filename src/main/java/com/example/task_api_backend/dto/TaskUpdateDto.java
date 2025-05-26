package com.example.task_api_backend.dto;

import lombok.Data;

@Data
public class TaskUpdateDto {
    
    private String description;
    private Boolean done;
}
