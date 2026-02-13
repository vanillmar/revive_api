/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.mapper.AuthRegisterMapper;
import com.example.revive_app.data.mapper.UserMapper;
import com.example.revive_app.model.Role;
import com.example.revive_app.model.User;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthRegisterMapper authRegisterMapper;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserServiceImpl userService,
            AuthRegisterMapper authRegisterMapper, RoleService roleService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.authRegisterMapper = authRegisterMapper;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) throws AuthenticationException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String jwt = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        long expiresIn = jwtService.getAccessTokenExpirationSeconds();

        return new AuthResponse(jwt, refreshToken, expiresIn);
    }

    @Override
    public AuthResponse register(AuthRegisterRequestDTO request) {
        Set<Role> roles = roleService.getAllRoles().stream().filter(role -> role.getId() == request.getRoleId())
                .collect(Collectors.toSet());

        UserRequestDTO userRequestDTO = authRegisterMapper.toUserRequestDTO(request);
        userRequestDTO.setRoles(roles);
        User user = userMapper.toEntity(userRequestDTO);
        User userSaved = userService.create(user);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSaved.getUsername(), userRequestDTO.getPassword()));
        UserDetails authenticatedUser = (UserDetails) auth.getPrincipal();
        String jwt = jwtService.generateAccessToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);
        long expiresIn = jwtService.getAccessTokenExpirationSeconds();
        return new AuthResponse(jwt, refreshToken, expiresIn);
    }

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
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
