package com.productdelivery.core.application.controllerAdvice;


import com.productdelivery.core.application.error.response.ApiErrorResponse;
import com.productdelivery.core.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errors =
            ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();

        return ResponseEntity.badRequest().body(createApiErrorResponse(errors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerResourceNotFoundException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(createApiErrorResponse(ex.getMessage()));
    }

    protected ApiErrorResponse createApiErrorResponse(List<String> errors) {
        return ApiErrorResponse.builder()
            .errors(errors)
            .status(HttpStatus.BAD_REQUEST.value())
            .errorCode(HttpStatus.BAD_REQUEST.name())
            .timestamp(LocalDateTime.now())
            .build();
    }

    protected ApiErrorResponse createApiErrorResponse(String error) {
        return ApiErrorResponse.builder()
            .errors(List.of(error))
            .status(HttpStatus.BAD_REQUEST.value())
            .errorCode(HttpStatus.BAD_REQUEST.name())
            .timestamp(LocalDateTime.now())
            .build();
    }
}
