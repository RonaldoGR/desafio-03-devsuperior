package com.example.desafio_03_devsuperior.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    List<FieldMessage> errorMessages = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public ValidationError(Instant timestamp, Integer status, String error, String path, List<FieldMessage> errorMessages) {
        super(timestamp, status, error, path);
        this.errorMessages = errorMessages;
    }

    public List<FieldMessage> getErrorMessages() {
        return errorMessages;
    }


    public void addError(String fieldName, String message) {
        errorMessages.add(new FieldMessage(fieldName, message));
    }
}
