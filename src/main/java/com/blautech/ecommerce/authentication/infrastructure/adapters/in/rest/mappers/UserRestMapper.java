package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers;

import com.blautech.ecommerce.authentication.domain.models.Role;
import com.blautech.ecommerce.authentication.domain.models.User;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.SignUpRequest;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.UserResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class UserRestMapper {
    private UserRestMapper() {}
    public static User signUpRequestToDomain(SignUpRequest signUpRequest) {
        return User.builder()
            .id(null)
            .firstname(signUpRequest.getFirstname())
            .lastname(signUpRequest.getLastname())
            .email(signUpRequest.getEmail())
            .password(signUpRequest.getPassword())
            .address(signUpRequest.getAddress())
            .birthday(signUpRequest.getBirthday())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
    public static UserResponse domainToResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .address(user.getAddress())
            .birthday(user.getBirthday())
            .roles(
                user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet())
            )
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
}
