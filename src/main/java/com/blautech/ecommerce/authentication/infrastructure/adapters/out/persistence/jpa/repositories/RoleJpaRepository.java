package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.repositories;

import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.entities.RoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
