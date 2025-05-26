package com.example.task_api_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_api_backend.dto.AuthRequestDto;
import com.example.task_api_backend.dto.AuthResponseDto;
import com.example.task_api_backend.dto.UserCreateDto;
import com.example.task_api_backend.dto.UserDto;
import com.example.task_api_backend.service.AuthService;
import com.example.task_api_backend.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserCreateDto dto) {
        authService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto dto) {
        return ResponseEntity.ok(authService.authenticate(dto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }
}