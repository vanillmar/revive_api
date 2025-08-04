package com.example.revive_app.controller;

import com.example.revive_app.data.dto.AuthErrorResponseDTO;
import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import com.example.revive_app.data.dto.ErrorResponseDTO;
import com.example.revive_app.service.AuthService;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<? super AuthResponse> login(@RequestBody AuthRequest request) {
    try {
      AuthResponse response = authService.authenticate(request);
      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      // Handle authentication failure
      ErrorResponseDTO errorResponse =
          new AuthErrorResponseDTO(
              Instant.now(),
              HttpStatus.UNAUTHORIZED.value(),
              "Unauthorized",
              "Invalid username or password",
              "/api/auth/login");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
  }
}
