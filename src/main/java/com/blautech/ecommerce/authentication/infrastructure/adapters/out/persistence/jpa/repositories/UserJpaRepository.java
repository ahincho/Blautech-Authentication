package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.repositories;

import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findById(Long id);
    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
