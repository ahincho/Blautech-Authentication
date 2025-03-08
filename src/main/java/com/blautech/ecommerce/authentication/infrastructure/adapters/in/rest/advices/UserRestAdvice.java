package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.advices;

import com.blautech.ecommerce.authentication.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserRestAdvice {
    @ExceptionHandler(UserUnderAgeException.class)
    public ResponseEntity<ExceptionResponse> userUnderAgeException(
        UserUnderAgeException userUnderAgeException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .statusDescription(HttpStatus.UNPROCESSABLE_ENTITY.name())
            .message(userUnderAgeException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionResponse);
    }
    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<ExceptionResponse> userDuplicateException(
        UserDuplicateException userDuplicateException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.CONFLICT.value())
            .statusDescription(HttpStatus.CONFLICT.name())
            .message(userDuplicateException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(
        UserNotFoundException userNotFoundException,
        HttpServletRequest httpServletRequest
    ) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .path(httpServletRequest.getRequestURI())
            .method(httpServletRequest.getMethod())
            .statusCode(HttpStatus.NOT_FOUND.value())
            .statusDescription(HttpStatus.NOT_FOUND.name())
            .message(userNotFoundException.getMessage())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
