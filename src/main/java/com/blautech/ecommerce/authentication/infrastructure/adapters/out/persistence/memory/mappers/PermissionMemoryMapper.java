package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.memory.mappers;

import com.blautech.ecommerce.authentication.domain.models.Permission;
import com.blautech.ecommerce.authentication.domain.models.Route;

public class PermissionMemoryMapper {
    private PermissionMemoryMapper() {}
    public static Route createOneRoute(String path, String method) {
        return Route.builder()
            .path(path)
            .method(method)
            .build();
    }
    public static Permission create(String role, Route route) {
        return Permission.builder()
            .role(role)
            .route(route)
            .build();
    }
}
