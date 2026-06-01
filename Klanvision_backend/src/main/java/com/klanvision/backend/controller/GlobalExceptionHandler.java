package com.klanvision.backend.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleConflict(DataIntegrityViolationException ex) {
        String msg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        if (msg != null && msg.contains("Duplicate entry")) {
            if (msg.contains("email") || msg.contains("EMAIL")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address is already registered.");
            }
            if (msg.contains("username") || msg.contains("USERNAME")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A record with these credentials already exists.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unique constraint violation: " + msg);
    }
}
