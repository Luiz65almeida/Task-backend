package com.example.task_api_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskCreateDto {

    @NotBlank
    @Size(max = 120)
    private String description;
}
