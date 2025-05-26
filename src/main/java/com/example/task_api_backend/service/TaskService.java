package com.example.task_api_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.task_api_backend.dto.TaskCreateDto;
import com.example.task_api_backend.dto.TaskDto;
import com.example.task_api_backend.dto.TaskUpdateDto;
import com.example.task_api_backend.model.Task;
import com.example.task_api_backend.model.User;
import com.example.task_api_backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskDto create(TaskCreateDto dto) {
        User user = userService.getCurrentUser();
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setDone(false);
        task.setOwner(user);
        Task saved = taskRepository.save(task);
        return mapToDto(saved);
    }
    
    public TaskDto update(Long id, TaskUpdateDto dto) {
        User user = userService.getCurrentUser();
        Task task = taskRepository.findById(id)
                .filter(t -> t.getOwner().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada ou acesso negado."));

        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getDone() != null) task.setDone(dto.getDone());

        Task updated = taskRepository.save(task);
        return mapToDto(updated);
    }

    public void delete(Long id) {
        User user = userService.getCurrentUser();
        Task task = taskRepository.findById(id)
                .filter(t -> t.getOwner().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada ou acesso negado."));
        taskRepository.delete(task);
    }

    public Page<TaskDto> list(Pageable pageable) {
        User user = userService.getCurrentUser();
        return taskRepository.findByOwnerId(user.getId(), pageable)
                .map(this::mapToDto);
    }

    public TaskDto getById(Long id) {
        User user = userService.getCurrentUser();
        Task task = taskRepository.findById(id)
                .filter(t -> t.getOwner().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada ou acesso negado."));
        return mapToDto(task);
    }

    private TaskDto mapToDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setDescription(task.getDescription());
        dto.setDone(task.isDone());
        return dto;
    }
}
