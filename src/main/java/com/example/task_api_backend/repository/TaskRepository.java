package com.example.task_api_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_api_backend.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByOwnerId(Long ownerId, Pageable pageable);
}
