package com.blautech.ecommerce.authentication.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credential {
    private String issuer;
    private Long userId;
    private String username;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
