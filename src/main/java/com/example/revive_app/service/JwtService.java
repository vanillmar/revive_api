/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.revive_app.model.User;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.access-expiration-minutes}")
    private long accessExpirationMinutes;

    @Value("${app.jwt.refresh-expiration-days}")
    private long refreshExpirationDays;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    /**
     * Generate a short-lived access token using the authenticated user.
     */
    public String generateAccessToken(UserDetails userDetails) {
        User user = (User) userDetails;
        String[] roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
        return JWT.create().withIssuer("auth-api").withSubject(userDetails.getUsername())
                .withClaim("id", user.getId().toString()).withIssuedAt(new Date()).withClaim("email", user.getEmail())
                .withIssuedAt(new Date()).withClaim("avatar", user.getAvatar()).withIssuedAt(new Date())
                .withArrayClaim("roles", roles).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessExpirationMinutes * 60 * 1000))
                .sign(getAlgorithm());
    }

    /**
     * Generate a long-lived refresh token.
     */
    public String generateRefreshToken(UserDetails userDetails) {
        User user = (User) userDetails;
        String username = user.getUsername();

        return JWT.create().withSubject(username).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpirationDays * 24 * 60 * 60 * 1000))
                .sign(getAlgorithm());
    }

    /**
     * Verify if a token is valid and not expired.
     */
    public boolean isTokenValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm()).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * Extract username (subject) from token.
     */
    public String extractUsername(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return decoded.getSubject();
    }

    /**
     * Extract roles claim from token.
     */
    public List<String> extractRoles(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return decoded.getClaim("roles").asList(String.class);
    }

    public long getAccessTokenExpirationSeconds() {
        return accessExpirationMinutes * 60;
    }

}
