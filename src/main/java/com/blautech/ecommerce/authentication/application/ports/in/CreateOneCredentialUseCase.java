package com.blautech.ecommerce.authentication.application.ports.in;

import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.authentication.domain.models.Credential;
import com.blautech.ecommerce.authentication.domain.models.User;

public interface CreateOneCredentialUseCase {
    Credential execute(User user) throws UserNotFoundException, UserCredentialsException;
}
