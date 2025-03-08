package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.advices;

import com.blautech.ecommerce.authentication.domain.exceptions.RoleNotFoundException;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RoleRestAdvice {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> roleNotFoundException(
        RoleNotFoundException roleNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .statusDescription(HttpStatus.NOT_FOUND.name())
            .message(roleNotFoundException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
