package com.blautech.ecommerce.authentication.infrastructure.adapters.out.jwt.io.implementations;

import com.blautech.ecommerce.authentication.application.ports.out.JwtProviderPort;
import com.blautech.ecommerce.authentication.domain.models.Credential;
import com.blautech.ecommerce.authentication.domain.models.Role;
import com.blautech.ecommerce.authentication.domain.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtIoProviderAdapter implements JwtProviderPort {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiration}")
    private Long expiration;
    private Key key;
    @PostConstruct
    protected void initialize() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
    @Override
    public Credential createOneCredential(User user) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 1000 * 60 * this.expiration);
        Map<String, Object> claims = Map.of(
            "userId", user.getId(),
            "firstname", user.getFirstname(),
            "lastname", user.getLastname(),
            "email", user.getEmail(),
            "address", user.getAddress(),
            "birthday", user.getBirthday().toString(),
            "roles", user.getRoles().stream().map(Role::getName).toList(),
            "issuedAt", issuedAt.toString(),
            "expiresAt", expiresAt.toString()
        );
        String jwtToken = Jwts.builder()
            .issuer(this.issuer)
            .subject(user.getEmail())
            .issuedAt(issuedAt)
            .expiration(expiresAt)
            .claims(claims)
            .signWith(this.key)
            .compact();
        return createFromToken(user.getId(), user.getEmail(), jwtToken, issuedAt, expiresAt);
    }
    @Override
    public boolean verifyOneToken(String token) {
        try {
            Jwts.parser()
                .verifyWith((SecretKey) this.key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public <T> Optional<T> getClaimFromToken(String token, String claimKey, Class<T> claimType) {
        try {
            T claim = Jwts.parser()
                .verifyWith((SecretKey) this.key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(claimKey, claimType);
            return Optional.ofNullable(claim);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
    protected Credential createFromToken(Long userId, String username, String token, Date createdAt, Date expiresAt) {
        return Credential.builder()
            .issuer(this.issuer)
            .userId(userId)
            .username(username)
            .token(token)
            .createdAt(convertToLocalDateTime(createdAt))
            .expiresAt(convertToLocalDateTime(expiresAt))
            .build();
    }
    protected LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
