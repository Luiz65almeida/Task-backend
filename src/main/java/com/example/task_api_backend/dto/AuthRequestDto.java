package com.example.task_api_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
