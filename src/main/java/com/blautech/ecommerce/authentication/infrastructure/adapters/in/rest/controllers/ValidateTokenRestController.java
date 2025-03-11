package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.authentication.application.ports.in.ValidateOneTokenUseCase;
import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.authentication.domain.models.Token;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.CheckResponse;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.TokenRequest;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers.CredentialRestMapper;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers.TokenRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class ValidateTokenRestController {
    private final ValidateOneTokenUseCase validateOneTokenUseCase;
    public ValidateTokenRestController(ValidateOneTokenUseCase validateOneTokenUseCase) {
        this.validateOneTokenUseCase = validateOneTokenUseCase;
    }
    @PostMapping
    public ResponseEntity<CheckResponse> validateOneToken(
        @RequestBody @Valid TokenRequest tokenRequest
    ) throws UserNotFoundException, UserCredentialsException {
        Token token = TokenRestMapper.tokenRequestToDomain(tokenRequest);
        System.out.println(token);
        boolean success = this.validateOneTokenUseCase.execute(token);
        System.out.println("Success: " + success);
        CheckResponse checkResponse = CredentialRestMapper.domainToCheckResponse(success);
        if (success) {
            return ResponseEntity.ok(checkResponse);
        } else {
            return ResponseEntity.badRequest().body(checkResponse);
        }
    }
}
