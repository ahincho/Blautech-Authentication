package com.blautech.ecommerce.authentication.application.ports.out;

import com.blautech.ecommerce.authentication.domain.models.Credential;
import com.blautech.ecommerce.authentication.domain.models.User;

import java.util.Optional;

public interface JwtProviderPort {
    Credential createOneCredential(User user);
    boolean verifyOneToken(String token);
    <T> Optional<T> getClaimFromToken(String token, String claimKey, Class<T> claimType);
}
