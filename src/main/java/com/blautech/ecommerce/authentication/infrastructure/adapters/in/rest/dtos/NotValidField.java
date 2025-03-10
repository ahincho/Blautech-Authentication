package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.ConstraintViolation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.validation.FieldError;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotValidField {
    private String field;
    private String message;
    public NotValidField(FieldError fieldError) {
        this.field = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }
    public NotValidField(ConstraintViolation<?> violation) {
        this.field = extractFieldName(violation.getPropertyPath().toString());
        this.message = violation.getMessage();
    }
    protected static String extractFieldName(String propertyPath) {
        String[] parts = propertyPath.split("\\.");
        return parts[parts.length - 1];
    }
}
