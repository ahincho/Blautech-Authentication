package com.blautech.ecommerce.authentication.application.services;

import com.blautech.ecommerce.authentication.application.ports.in.ValidateOnePermissionUseCase;
import com.blautech.ecommerce.authentication.application.ports.out.PermissionPersistencePort;
import com.blautech.ecommerce.authentication.domain.models.Permission;

import org.springframework.stereotype.Service;

@Service
public class ValidateOnePermissionDefaultService implements ValidateOnePermissionUseCase {
    private final PermissionPersistencePort permissionPersistencePort;
    public ValidateOnePermissionDefaultService(PermissionPersistencePort permissionPersistencePort) {
        this.permissionPersistencePort = permissionPersistencePort;
    }
    @Override
    public boolean execute(Permission permission) {
        return this.permissionPersistencePort.existsOnePermission(permission);
    }
}
