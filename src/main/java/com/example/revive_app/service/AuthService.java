package com.example.revive_app.service;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import java.util.Optional;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    AuthResponse authenticate(AuthRequest request) throws AuthenticationException;

    AuthResponse register(AuthRegisterRequestDTO request);

    Optional<UserDetails> findByUsername(String username);

    AuthResponse autenticateWithToken(String oldToken);
}