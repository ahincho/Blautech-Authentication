package com.blautech.ecommerce.authentication.application.ports.in;

import com.blautech.ecommerce.authentication.domain.models.Permission;

public interface ValidateOnePermissionUseCase {
    boolean execute(Permission permission);
}
