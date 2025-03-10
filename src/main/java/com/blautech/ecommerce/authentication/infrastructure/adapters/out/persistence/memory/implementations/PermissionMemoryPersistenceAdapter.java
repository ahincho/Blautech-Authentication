package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.memory.implementations;

import com.blautech.ecommerce.authentication.application.ports.out.PermissionPersistencePort;
import com.blautech.ecommerce.authentication.domain.models.Permission;
import com.blautech.ecommerce.authentication.domain.models.Route;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.memory.mappers.PermissionMemoryMapper;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class PermissionMemoryPersistenceAdapter implements PermissionPersistencePort {
    private final Set<Permission> permissions;
    public PermissionMemoryPersistenceAdapter() {
        this.permissions = initializePermissions();
    }
    @Override
    public boolean existsOnePermission(Permission permission) {
        return this.permissions.contains(permission);
    }
    protected Set<Permission> initializePermissions() {
        // Roles
        String publicRole = "Public";
        String customerRole = "Customer";
        String administratorRole = "Administrator";
        // Paths
        String categoriesPath = "/api/v1/categories";
        String productsPath = "/api/v1/products";
        String usersPath = "/api/v1/users";
        String ordersPath = "/api/v1/orders";
        String authPath = "/api/v1/auth";
        // Methods
        String postMethod = HttpMethod.POST.name();
        String getMethod = HttpMethod.GET.name();
        String putMethod = HttpMethod.PUT.name();
        String patchMethod = HttpMethod.PATCH.name();
        String deleteMethod = HttpMethod.DELETE.name();
        // Routes
        Route createOneProductRoute = PermissionMemoryMapper.createOneRoute(productsPath, postMethod);
        Route findProductsRoute = PermissionMemoryMapper.createOneRoute(productsPath, getMethod);
        // Permissions
        Set<Permission> defaultPermissions = new HashSet<>();
        defaultPermissions.add(PermissionMemoryMapper.create(administratorRole, createOneProductRoute));
        defaultPermissions.add(PermissionMemoryMapper.create(administratorRole, findProductsRoute));
        defaultPermissions.add(PermissionMemoryMapper.create(customerRole, findProductsRoute));
        return defaultPermissions;
    }
}
