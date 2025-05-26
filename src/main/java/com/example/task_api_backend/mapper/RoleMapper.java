package com.example.task_api_backend.mapper;

import com.example.task_api_backend.dto.RoleDto;
import com.example.task_api_backend.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
}
