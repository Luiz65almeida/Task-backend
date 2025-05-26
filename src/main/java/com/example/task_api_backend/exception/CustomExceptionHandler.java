package com.example.task_api_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.task_api_backend.dto.ApiErrorMessage;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorMessage> handleRuntime(RuntimeException ex, WebRequest request) {
        ApiErrorMessage error = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Runtime Exception",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Erro de validação");

        ApiErrorMessage error = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                message,
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorMessage> handleConstraint(ConstraintViolationException ex, WebRequest request) {
        ApiErrorMessage error = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorMessage> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ApiErrorMessage error = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Type Mismatch",
                "Parâmetro inválido: " + ex.getName(),
                request.getDescription(false)
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorMessage> handleGeneric(Exception ex, WebRequest request) {
        ApiErrorMessage error = new ApiErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getDescription(false) 
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
}