package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    @Size(min = 1, message = "Token must be at least 1 character")
    private String token;
    @Valid
    @NotNull(message = "Permission details are required")
    private PermissionRequest permission;
}
