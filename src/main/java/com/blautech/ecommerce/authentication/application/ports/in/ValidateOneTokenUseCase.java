package com.blautech.ecommerce.authentication.application.ports.in;

import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.authentication.domain.models.Token;

public interface ValidateOneTokenUseCase {
    boolean execute(Token token) throws UserNotFoundException, UserCredentialsException;
}
