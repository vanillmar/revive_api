package com.example.revive_app.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token);

    String extractUsername(String token);

    List<String> extractRoles(String token);

    long getAccessTokenExpirationSeconds();
}
