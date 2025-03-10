package com.blautech.ecommerce.authentication.domain.exceptions;

public class PermissionNotFoundException extends Exception {
    public PermissionNotFoundException(String message) {
        super(message);
    }
}
