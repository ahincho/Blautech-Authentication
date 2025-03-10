package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.mappers;

import com.blautech.ecommerce.authentication.domain.models.Role;
import com.blautech.ecommerce.authentication.domain.models.User;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.entities.RoleEntity;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

import java.util.Collections;
import java.util.stream.Collectors;

public class UserJpaMapper {
    private UserJpaMapper() {}
    public static UserEntity domainToEntity(User user) {
        return UserEntity.builder()
            .id(user.getId() == null ? null : user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .password(user.getPassword())
            .address(user.getAddress())
            .birthday(user.getBirthday())
            .createdAt(user.getCreatedAt() == null ? null : user.getCreatedAt())
            .updatedAt(user.getUpdatedAt() == null ? null : user.getUpdatedAt())
            .build();
    }
    public static User entityToDomain(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .firstname(userEntity.getFirstname())
            .lastname(userEntity.getLastname())
            .email(userEntity.getEmail())
            .password(userEntity.getPassword())
            .address(userEntity.getAddress())
            .birthday(userEntity.getBirthday())
            .roles(
                userEntity.getRoles() == null || userEntity.getRoles().isEmpty()
                    ? Collections.emptySet()
                    : userEntity.getRoles().stream()
                    .map(UserJpaMapper::entityRoleToDomainRole)
                    .collect(Collectors.toSet())
            )
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .build();
    }
    public static Role entityRoleToDomainRole(RoleEntity roleEntity) {
        return Role.builder()
            .id(roleEntity.getId())
            .name(roleEntity.getName())
            .build();
    }
}
