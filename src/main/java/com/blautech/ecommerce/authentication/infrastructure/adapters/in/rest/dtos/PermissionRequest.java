package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {
    @NotBlank(message = "Path cannot be empty")
    @Size(min = 1, message = "Path must have at least 1 character")
    private String path;
    @NotBlank(message = "Method cannot be empty")
    @Size(min = 1, message = "Method must have at least 1 character")
    private String method;
}
