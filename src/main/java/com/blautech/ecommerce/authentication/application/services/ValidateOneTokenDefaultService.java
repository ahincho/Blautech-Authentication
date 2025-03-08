package com.blautech.ecommerce.authentication.application.services;

import com.blautech.ecommerce.authentication.application.ports.in.ValidateOneTokenUseCase;
import com.blautech.ecommerce.authentication.application.ports.out.JwtProviderPort;
import com.blautech.ecommerce.authentication.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateOneTokenDefaultService implements ValidateOneTokenUseCase {
    private final UserPersistencePort userPersistencePort;
    private final JwtProviderPort jwtProviderPort;
    public ValidateOneTokenDefaultService(
        UserPersistencePort userPersistencePort,
        JwtProviderPort jwtProviderPort
    ) {
        this.userPersistencePort = userPersistencePort;
        this.jwtProviderPort = jwtProviderPort;
    }
    @Override
    public boolean execute(String token) throws UserNotFoundException, UserCredentialsException {
        if (!this.jwtProviderPort.verifyOneToken(token)) {
            return false;
        }
        Optional<Long> optionalUserId = this.jwtProviderPort.getClaimFromToken(token, "userId", Long.class);
        return optionalUserId.filter(this.userPersistencePort::existsOneUserById).isPresent();
    }
}
