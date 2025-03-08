package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.mappers;

import com.blautech.ecommerce.authentication.domain.models.Permission;
import com.blautech.ecommerce.authentication.domain.models.Route;
import com.blautech.ecommerce.authentication.domain.models.Token;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.PermissionRequest;
import com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos.TokenRequest;

public class TokenRestMapper {
    private TokenRestMapper() {}
    public static Permission permissionRequestToDomain(PermissionRequest permissionRequest) {
        return Permission.builder()
            .route(
                Route.builder()
                    .path(permissionRequest.getPath())
                    .method(permissionRequest.getPath())
                    .build()
            )
            .build();
    }
    public static Token tokenRequestToDomain(TokenRequest tokenRequest) {
        return Token.builder()
            .payload(tokenRequest.getToken())
            .permission(
                Permission.builder()
                    .route(
                        Route.builder()
                            .path(tokenRequest.getPermission().getPath())
                            .method(tokenRequest.getPermission().getMethod())
                            .build()
                    )
                    .build()
            )
            .build();
    }
}
