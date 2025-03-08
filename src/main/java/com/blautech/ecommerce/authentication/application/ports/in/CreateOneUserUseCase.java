package com.blautech.ecommerce.authentication.application.ports.in;

import com.blautech.ecommerce.authentication.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.authentication.domain.models.User;

public interface CreateOneUserUseCase {
    User execute(User user) throws UserUnderAgeException, UserDuplicateException;
}
