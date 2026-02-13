/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.model.Role;
import com.example.revive_app.service.AuthService;
import com.example.revive_app.service.JwtService;
import com.example.revive_app.service.RoleService;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final RoleService roleService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, RoleService roleService, JwtService jwtService) {
        this.authService = authService;
        this.roleService = roleService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<AuthResponse>> register(@RequestBody AuthRegisterRequestDTO authDTO) {
        AuthResponse response = authService.register(authDTO);
        ResponseDTO<AuthResponse> resp = new ResponseDTO<>();
        resp.setStatus(HttpStatus.CREATED.value());
        resp.setMessage("User registered successfully");
        resp.setSuccess(true);
        resp.setData(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponse>> login(@RequestBody AuthRequest authDTO) {
        ResponseDTO<AuthResponse> response = new ResponseDTO<>();
        try {
            AuthResponse data = authService.authenticate(authDTO);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Login successful");
            response.setSuccess(true);
            response.setData(data);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid username or password");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDTO<AuthResponse>> refresh(@RequestBody Map<String, String> body) {
        ResponseDTO<AuthResponse> response = new ResponseDTO<>();
        String refreshToken = body.get("refreshToken");

        if (!jwtService.isTokenValid(refreshToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid or expired refresh token");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        AuthResponse authResponse = authService.autenticateWithToken(refreshToken);

        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Token refreshed successfully");
        response.setSuccess(true);
        response.setData(authResponse);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/roles")
    public ResponseEntity<ResponseDTO<Set<Role>>> getRoles() {
        ResponseDTO<Set<Role>> response = new ResponseDTO<>();
        Set<Role> roles = roleService.getAllRoles();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Roles fetched successfully");
        response.setSuccess(true);
        response.setData(roles);
        response.setTotal(roles.size());
        return ResponseEntity.ok(response);
    }
}
