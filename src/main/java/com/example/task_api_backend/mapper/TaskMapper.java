package com.example.task_api_backend.mapper;

import com.example.task_api_backend.dto.TaskCreateDto;
import com.example.task_api_backend.dto.TaskDto;
import com.example.task_api_backend.dto.TaskUpdateDto;
import com.example.task_api_backend.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskCreateDto dto);
    Task toEntity(TaskUpdateDto dto);
}
