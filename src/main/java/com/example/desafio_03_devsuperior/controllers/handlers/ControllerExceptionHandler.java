package com.example.desafio_03_devsuperior.controllers.handlers;

import com.example.desafio_03_devsuperior.dto.CustomError;
import com.example.desafio_03_devsuperior.dto.ValidationError;
import com.example.desafio_03_devsuperior.services.exceptions.ClientNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler  {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<CustomError> handleClientNotFoundException(ClientNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), status.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Invalid data", request.getRequestURI());

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            err.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

}
