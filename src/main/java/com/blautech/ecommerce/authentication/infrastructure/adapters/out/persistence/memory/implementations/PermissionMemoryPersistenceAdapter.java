package com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.memory.implementations;

import com.blautech.ecommerce.authentication.application.ports.out.PermissionPersistencePort;
import com.blautech.ecommerce.authentication.domain.models.Permission;
import com.blautech.ecommerce.authentication.domain.models.Route;
import com.blautech.ecommerce.authentication.infrastructure.adapters.out.persistence.memory.mappers.PermissionMemoryMapper;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PermissionMemoryPersistenceAdapter implements PermissionPersistencePort {
    private Set<Route> publicRoutes = new HashSet<>();
    private final Set<Permission> permissions = new HashSet<>();
    public PermissionMemoryPersistenceAdapter() {
        initializePublicRoutes();
        initializePermissions();
        System.out.println("=== Public Routes ===");
        publicRoutes.forEach(route -> System.out.println("Route: " + route));
        System.out.println("=== Permissions ===");
        permissions.forEach(permission -> System.out.println("Permission: " + permission.getRole() + ", Route: " + permission.getRoute()));
    }
    @Override
    public boolean existsOnePermission(Permission permission) {
        return publicRoutes.contains(permission.getRoute()) || permissions.contains(permission);
    }
    private void initializePublicRoutes() {
        publicRoutes = Set.of(
            createRoute(ApiPaths.CATEGORIES, HttpMethod.GET),
            createRoute(ApiPaths.PRODUCTS, HttpMethod.GET),
            createRoute(ApiPaths.PRODUCTS_IDS, HttpMethod.POST),
            createRoute(ApiPaths.ORDERS, HttpMethod.GET),
            createRoute(ApiPaths.ORDERS, HttpMethod.POST)
        );
    }
    private void initializePermissions() {
        // Administrador
        addPermissions(permissions, UserRole.ADMINISTRATOR, ApiPaths.CATEGORIES, List.of(HttpMethod.GET));
        addPermissions(permissions, UserRole.ADMINISTRATOR, ApiPaths.PRODUCTS, List.of(HttpMethod.POST, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE));
        addPermissions(permissions, UserRole.ADMINISTRATOR, ApiPaths.PRODUCTS_IDS, List.of(HttpMethod.POST));
        addPermissions(permissions, UserRole.ADMINISTRATOR, ApiPaths.USERS, List.of(HttpMethod.POST, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE));
        addPermissions(permissions, UserRole.ADMINISTRATOR, ApiPaths.ORDERS, List.of(HttpMethod.POST, HttpMethod.GET));
        // Cliente
        addPermissions(permissions, UserRole.CUSTOMER, ApiPaths.PRODUCTS, List.of(HttpMethod.GET, HttpMethod.PUT));
        addPermissions(permissions, UserRole.CUSTOMER, ApiPaths.PRODUCTS_IDS, List.of(HttpMethod.PUT));
        addPermissions(permissions, UserRole.CUSTOMER, ApiPaths.ORDERS, List.of(HttpMethod.GET));
    }
    private void addPermissions(Set<Permission> permissions, UserRole role, String path, List<HttpMethod> httpMethods) {
        for (HttpMethod httpMethod : httpMethods) {
            permissions.add(PermissionMemoryMapper.create(role.name(), createRoute(path, httpMethod)));
        }
    }
    private Route createRoute(String path, HttpMethod method) {
        return PermissionMemoryMapper.createOneRoute(path, method.name());
    }
    private static class ApiPaths {
        public static final String CATEGORIES = "/api/v1/categories";
        public static final String PRODUCTS = "/api/v1/products";
        public static final String PRODUCTS_IDS = "/api/v1/products/ids";
        public static final String USERS = "/api/v1/users";
        public static final String ORDERS = "/api/v1/orders";
    }
    private enum UserRole {
        CUSTOMER, ADMINISTRATOR
    }
}
