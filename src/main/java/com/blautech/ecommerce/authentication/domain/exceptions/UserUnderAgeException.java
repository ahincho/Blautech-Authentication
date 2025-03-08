package com.blautech.ecommerce.authentication.domain.exceptions;

public class UserUnderAgeException extends Exception {
    public UserUnderAgeException(String message) {
        super(message);
    }
}
