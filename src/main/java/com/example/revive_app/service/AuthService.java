package com.example.revive_app.service;

import com.example.revive_app.data.dto.AuthRegisterRequestDTO;
import com.example.revive_app.data.dto.AuthRequest;
import com.example.revive_app.data.dto.AuthResponse;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.data.mapper.AuthRegisterMapper;
import com.example.revive_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final AuthRegisterMapper authRegisterMapper;

  @Autowired
  public AuthService(
      AuthenticationManager authenticationManager,
      JwtService jwtService,
      UserService userService,
      AuthRegisterMapper authRegisterMapper) {
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.userService = userService;
    this.authRegisterMapper = authRegisterMapper;
  }

  public AuthResponse authenticate(AuthRequest request) throws AuthenticationException {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    User user = (User) authentication.getPrincipal();
    String jwt = jwtService.generateToken(user);

    return new AuthResponse(user.getId().toString(), jwt, user.getUsername());
  }

  public AuthResponse register(AuthRegisterRequestDTO request) {
    UserRequestDTO userDTO = authRegisterMapper.toUserRequestDTO(request);
    UserResponseDTO newUser = userService.createUser(userDTO);
    String jwt = "";

    return new AuthResponse(newUser.getId().toString(), jwt, newUser.getUsername());
  }
}
