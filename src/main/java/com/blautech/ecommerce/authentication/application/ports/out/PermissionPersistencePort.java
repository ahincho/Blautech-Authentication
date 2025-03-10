package com.blautech.ecommerce.authentication.application.ports.out;

import com.blautech.ecommerce.authentication.domain.models.Permission;

public interface PermissionPersistencePort {
    boolean existsOnePermission(Permission permission);
}
