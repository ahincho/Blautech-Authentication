package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.authentication.application.ports.in.CreateOneUserUseCase;
import com.blautech.ecommerce.authentication.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.authentication.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.authentication.domain.models.User;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.SignUpRequest;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth/sign/up")
public class SignUpRestController {
    private final CreateOneUserUseCase createOneUserUseCase;
    public SignUpRestController(CreateOneUserUseCase createOneUserUseCase) {
        this.createOneUserUseCase = createOneUserUseCase;
    }
    @PostMapping
    public ResponseEntity<UserResponse> signUp(
        @RequestBody @Valid SignUpRequest signUpRequest
    ) throws UserUnderAgeException, UserDuplicateException {
        User user = UserRestMapper.signUpRequestToDomain(signUpRequest);
        User savedUser = this.createOneUserUseCase.execute(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(UserRestMapper.domainToResponse(savedUser));
    }
}
