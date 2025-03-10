package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers;

import com.blautech.ecommerce.authentication.domain.models.Credential;
import com.blautech.ecommerce.authentication.domain.models.User;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.CheckResponse;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.CredentialResponse;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.SignInRequest;

public class CredentialRestMapper {
    private CredentialRestMapper() {}
    public static User signInRequestToUser(SignInRequest signInRequest) {
        return User.builder()
            .email(signInRequest.getEmail())
            .password(signInRequest.getPassword())
            .build();
    }
    public static CredentialResponse domainToResponse(Credential credential) {
        return CredentialResponse.builder()
            .issuer(credential.getIssuer())
            .userId(credential.getUserId())
            .username(credential.getUsername())
            .token(credential.getToken())
            .createdAt(credential.getCreatedAt())
            .expiresAt(credential.getExpiresAt())
            .build();
    }
    public static CheckResponse domainToCheckResponse(boolean success) {
        return CheckResponse.builder()
            .success(success)
            .build();
    }
}
