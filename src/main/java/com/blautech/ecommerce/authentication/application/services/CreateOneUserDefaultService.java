package com.blautech.ecommerce.authentication.application.services;

import com.blautech.ecommerce.authentication.application.ports.in.CreateOneUserUseCase;
import com.blautech.ecommerce.authentication.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.authentication.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.authentication.domain.models.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateOneUserDefaultService implements CreateOneUserUseCase {
    private static final Integer REQUIRED_AGE = 18;
    private final UserPersistencePort userPersistencePort;
    public CreateOneUserDefaultService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(User user) throws UserUnderAgeException, UserDuplicateException {
        validateUserAge(user.getBirthday());
        validateEmailUniqueness(user.getEmail());
        return this.userPersistencePort.createOneUser(user);
    }
    protected void validateUserAge(LocalDate birthday) throws UserUnderAgeException {
        LocalDate legalAgeDate = birthday.plusYears(REQUIRED_AGE);
        if (legalAgeDate.isAfter(LocalDate.now())) {
            throw new UserUnderAgeException(String.format("User must be at least %d years old.", REQUIRED_AGE));
        }
    }
    protected void validateEmailUniqueness(String email) throws UserDuplicateException {
        if (this.userPersistencePort.existsOneUserByEmail(email)) {
            throw new UserDuplicateException(String.format("User with email '%s' already exists", email));
        }
    }
}
