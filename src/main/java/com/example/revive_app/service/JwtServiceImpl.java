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
public class JwtServiceImpl implements JwtService {
    private static final String ISSUER = "auth-api";
    private static final String ID_CLAIM = "id";
    private static final String EMAIL_CLAIM = "email";
    private static final String AVATAR_CLAIM = "avatar";
    private static final String ROLES_CLAIM = "roles";
    private static final int MINUTES_TO_SECONDS = 60;
    private static final int HOURS_TO_MINUTES = 60;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final int HOURS_PER_DAY = 24;

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.access-expiration-minutes}")
    private long accessExpirationMinutes;

    @Value("${app.jwt.refresh-expiration-days}")
    private long refreshExpirationDays;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        User user = (User) userDetails;
        String[] roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
        return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(userDetails.getUsername())
            .withClaim(ID_CLAIM, user.getId().toString())
            .withClaim(EMAIL_CLAIM, user.getEmail())
            .withClaim(AVATAR_CLAIM, user.getAvatar())
            .withArrayClaim(ROLES_CLAIM, roles)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + accessExpirationMinutes * MINUTES_TO_SECONDS * MILLISECONDS_PER_SECOND))
            .sign(getAlgorithm());
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        String username = user.getUsername();

        return JWT.create().withSubject(username).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpirationDays * HOURS_PER_DAY * HOURS_TO_MINUTES * MINUTES_TO_SECONDS * MILLISECONDS_PER_SECOND))
                .sign(getAlgorithm());
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm()).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    @Override
    public String extractUsername(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return decoded.getSubject();
    }

    @Override
    public List<String> extractRoles(String token) {
        DecodedJWT decoded = JWT.decode(token);
        return decoded.getClaim(ROLES_CLAIM).asList(String.class);
    }

    @Override
    public long getAccessTokenExpirationSeconds() {
        return accessExpirationMinutes * MINUTES_TO_SECONDS;
    }

}
