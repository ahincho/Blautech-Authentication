package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.implementations;

import com.blautech.ecommerce.authentication.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.authentication.domain.exceptions.RoleNotFoundException;
import com.blautech.ecommerce.authentication.domain.models.User;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.entities.RoleEntity;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.mappers.UserJpaMapper;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.repositories.RoleJpaRepository;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.jpa.repositories.UserJpaRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public class UserSqlPersistenceAdapter implements UserPersistencePort {
    private static final String DEFAULT_ROLE = "Customer";
    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;
    public UserSqlPersistenceAdapter(
        UserJpaRepository userJpaRepository,
        RoleJpaRepository roleJpaRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public User createOneUser(User user) {
        UserEntity userEntity = UserJpaMapper.domainToEntity(user);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        Optional<RoleEntity> roleEntity = this.roleJpaRepository.findByName(DEFAULT_ROLE);
        if (roleEntity.isEmpty()) {
            throw new RoleNotFoundException(String.format("Default role %s not found", DEFAULT_ROLE));
        }
        userEntity.setRoles(Set.of(roleEntity.get()));
        UserEntity savedUserEntity = this.userJpaRepository.save(userEntity);
        return UserJpaMapper.entityToDomain(savedUserEntity);
    }
    @Override
    public Optional<User> findOneUserByEmail(String userEmail) {
        return this.userJpaRepository.findByEmail(userEmail).map(UserJpaMapper::entityToDomain);
    }
    @Override
    public boolean existsOneUserById(Long userId) {
        return this.userJpaRepository.existsById(userId);
    }
    @Override
    public boolean existsOneUserByEmail(String email) {
        return this.userJpaRepository.existsByEmail(email);
    }
}
