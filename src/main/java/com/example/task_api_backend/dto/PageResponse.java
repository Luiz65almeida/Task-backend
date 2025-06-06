package com.example.task_api_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    
    private List<T> data;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
}
