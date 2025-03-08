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
public class TokenRequest {
    @NotBlank(message = "Token is required")
    @Size(min = 1, message = "Token must be at least 1 character")
    private String token;
}
