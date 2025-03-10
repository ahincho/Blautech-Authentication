package com.blautech.ecommerce.authentication.application.ports.out;

import com.blautech.ecommerce.authentication.domain.models.User;

import java.util.Optional;

public interface UserPersistencePort {
    User createOneUser(User user);
    Optional<User> findOneUserByEmail(String userEmail);
    boolean existsOneUserById(Long userId);
    boolean existsOneUserByEmail(String email);
}
