package com.blautech.ecommerce.authentication.infrastructure.adapters.in.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialResponse {
    private String issuer;
    private Long userId;
    private String username;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
