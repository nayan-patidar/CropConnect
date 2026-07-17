package com.example.cropconnect.exception;

import com.example.cropconnect.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException ex) {

        String error = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, error, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {

        return ResponseEntity.internalServerError()
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }
}