/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import com.example.revive_app.data.mapper.AuthRegisterMapper;
import com.example.revive_app.model.User;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.revive_app.data.dto.UserRequestDTO;
@Service
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthRegisterMapper authRegisterMapper;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService,
            AuthRegisterMapper authRegisterMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.authRegisterMapper = authRegisterMapper;
    }

    public AuthResponse authenticate(AuthRequest request) throws AuthenticationException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String jwt = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        long expiresIn = jwtService.getAccessTokenExpirationSeconds();

        return new AuthResponse(jwt, refreshToken, expiresIn);
    }

    public AuthResponse register(AuthRegisterRequestDTO request) {
        UserRequestDTO userRequestDTO = authRegisterMapper.toUserRequestDTO(request);
        User userCreated = userService.createUser(userRequestDTO);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword()));
        UserDetails authenticatedUser = (UserDetails) auth.getPrincipal();
        String jwt = jwtService.generateAccessToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);
        long expiresIn = jwtService.getAccessTokenExpirationSeconds();
        return new AuthResponse(jwt, refreshToken, expiresIn);
    }

    public Optional<UserDetails> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    public Authentication authenticateUser(String username, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
    public AuthResponse autenticateWithToken(String oldToken) {
        String username = jwtService.extractUsername(oldToken);
        UserDetails user = this.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        UserDetails authenticatedUser = (UserDetails) auth.getPrincipal();
        String jwt = jwtService.generateAccessToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);
        long expiresIn = jwtService.getAccessTokenExpirationSeconds();
        return new AuthResponse(jwt, refreshToken, expiresIn);
    }
}
