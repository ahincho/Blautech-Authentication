package com.blautech.ecommerce.authentication.application.services;

import com.blautech.ecommerce.authentication.application.ports.in.CreateOneCredentialUseCase;
import com.blautech.ecommerce.authentication.application.ports.out.JwtProviderPort;
import com.blautech.ecommerce.authentication.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.authentication.domain.models.Credential;
import com.blautech.ecommerce.authentication.domain.models.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateOneCredentialDefaultService implements CreateOneCredentialUseCase {
    private final UserPersistencePort userPersistencePort;
    private final JwtProviderPort jwtProviderPort;
    private final PasswordEncoder passwordEncoder;
    public CreateOneCredentialDefaultService(
        UserPersistencePort userPersistencePort,
        JwtProviderPort jwtProviderPort,
        PasswordEncoder passwordEncoder
    ) {
        this.userPersistencePort = userPersistencePort;
        this.jwtProviderPort = jwtProviderPort;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Credential execute(User user) throws UserNotFoundException, UserCredentialsException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUserByEmail(user.getEmail());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email '%s' not found", user.getEmail()));
        }
        if (!this.passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())) {
            throw new UserCredentialsException("User has incorrect email or password");
        }
        return this.jwtProviderPort.createOneCredential(optionalUser.get());
    }
}
