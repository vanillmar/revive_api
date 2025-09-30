package com.example.revive_app.controller;

import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import com.example.revive_app.service.AuthService;
import com.example.revive_app.service.RoleService;
import com.example.revive_app.data.dto.ResponseDTO;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.RoleResponseDTO;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;
  private final RoleService roleService;

  @Autowired
  public AuthController(AuthService authService, RoleService roleService) {
    this.authService = authService;
    this.roleService = roleService;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody AuthRegisterRequestDTO request) {
    AuthResponse response = authService.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO<AuthResponse>> login(@RequestBody AuthRequest request) {
    ResponseDTO<AuthResponse> response = new ResponseDTO<>();
    try {
      AuthResponse data = authService.authenticate(request);
      response.setTimestamp(Instant.now());
      response.setStatus(HttpStatus.OK.value());
      response.setMessage("Login successful");
      response.setSuccess(true);
      response.setData(data);
      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      // Handle authentication failure
      response.setTimestamp(Instant.now());
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setMessage("Invalid username or password");
      response.setSuccess(false);
      response.setData(null);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  }

  @PostMapping("/roles")
  public ResponseEntity<ResponseDTO<RoleResponseDTO>> getRoles() { 
    ResponseDTO<RoleResponseDTO> response = new ResponseDTO<>();
    RoleResponseDTO data = new RoleResponseDTO(roleService.getAllRoles());
    response.setTimestamp(Instant.now());
    response.setStatus(HttpStatus.OK.value());
    response.setMessage("Roles fetched successfully");
    response.setSuccess(true);
    response.setData(data);
    return ResponseEntity.ok(response);
  }

}