package com.blautech.ecommerce.authentication.domain.exceptions;

public class UserDuplicateException extends Exception {
    public UserDuplicateException(String message) {
        super(message);
    }
}
