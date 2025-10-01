package com.example.revive_app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final Algorithm algorithm = Algorithm.HMAC256("my_secret");

  public String generateToken(UserDetails userDetails) {
    return JWT.create()
        .withIssuer("auth-api")
        .withSubject(userDetails.getUsername())
        .withClaim("roles", userDetails.getAuthorities().toString())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
        .sign(algorithm);
  }

  public String extractUsername(String token) {
    return JWT.require(algorithm).build().verify(token).getSubject();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    return extractUsername(token).equals(userDetails.getUsername());
  }

  public boolean isTokenValid(String token) {
    try {
      JWT.require(algorithm).build().verify(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
