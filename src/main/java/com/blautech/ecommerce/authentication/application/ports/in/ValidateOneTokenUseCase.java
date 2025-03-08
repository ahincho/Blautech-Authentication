package com.blautech.ecommerce.authentication.application.ports.in;

import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;

public interface ValidateOneTokenUseCase {
    boolean execute(String token) throws UserNotFoundException, UserCredentialsException;
}
