package com.example.task_api_backend.mapper;

import com.example.task_api_backend.dto.UserCreateDto;
import com.example.task_api_backend.dto.UserDto;
import com.example.task_api_backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserCreateDto dto);
}
