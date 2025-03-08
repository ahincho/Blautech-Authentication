package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.authentication.application.ports.in.CreateOneCredentialUseCase;
import com.blautech.ecommerce.authentication.domain.exceptions.UserCredentialsException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.authentication.domain.models.Credential;
import com.blautech.ecommerce.authentication.domain.models.User;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.CredentialResponse;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.SignInRequest;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers.CredentialRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth/sign/in")
public class SignInRestController {
    private final CreateOneCredentialUseCase createOneCredentialUseCase;
    public SignInRestController(CreateOneCredentialUseCase createOneCredentialUseCase) {
        this.createOneCredentialUseCase = createOneCredentialUseCase;
    }
    @PostMapping
    public ResponseEntity<CredentialResponse> signIn(
        @RequestBody @Valid SignInRequest signInRequest
    ) throws UserNotFoundException, UserCredentialsException {
        User user = CredentialRestMapper.signInRequestToUser(signInRequest);
        Credential credential = this.createOneCredentialUseCase.execute(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(CredentialRestMapper.domainToResponse(credential));
    }
}
