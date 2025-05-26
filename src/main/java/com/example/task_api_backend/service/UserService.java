package com.example.task_api_backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.task_api_backend.dto.UserDto;
import com.example.task_api_backend.mapper.UserMapper; // [ADICIONADO]
import com.example.task_api_backend.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper; // [ADICIONADO]

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserDto getProfile() {
        User user = getCurrentUser();
        return userMapper.toDto(user); // [SUBSTITUI MAPEAMENTO MANUAL]
    }
}
