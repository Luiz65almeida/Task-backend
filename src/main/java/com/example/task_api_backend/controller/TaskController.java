package com.example.task_api_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_api_backend.dto.PageResponse;
import com.example.task_api_backend.dto.TaskCreateDto;
import com.example.task_api_backend.dto.TaskDto;
import com.example.task_api_backend.dto.TaskUpdateDto;
import com.example.task_api_backend.service.TaskService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody @Valid TaskCreateDto dto) {
        return ResponseEntity.ok(taskService.create(dto));
    }

    @GetMapping
    public ResponseEntity<PageResponse<TaskDto>> list(Pageable pageable) {
        Page<TaskDto> pageResult = taskService.list(pageable);

        PageResponse<TaskDto> response = new PageResponse<>(
            pageResult.getContent(),
            pageResult.getNumber(),
            pageResult.getSize(),
            pageResult.getTotalPages(),
            pageResult.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody TaskUpdateDto dto) {
        return ResponseEntity.ok(taskService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

