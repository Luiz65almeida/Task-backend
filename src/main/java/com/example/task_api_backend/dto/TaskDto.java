package com.example.task_api_backend.dto;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String description;
    private boolean done;
}
