package com.yam.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=RequestException.class)
    public ResponseEntity<?> handleRequestException(RequestException e) {
        return ResponseEntity.badRequest()
                .body(Map.of("message", e.getMessage()));
    }

}
