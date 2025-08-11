package com.example.revive_app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.data.Permissions;
import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("hasAuthority('" + Permissions.READ_ME + "')")
  @GetMapping("/me")
  public ResponseEntity<UserResponseDTO> getMe(Authentication authentication) {
    UserResponseDTO user = userService.getMe(authentication.getPrincipal());
    return ResponseEntity.ok(user);
  }

  @PreAuthorize("hasAuthority('" + Permissions.READ_USERS + "')")
  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<UserResponseDTO> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @PreAuthorize("hasAuthority('" + Permissions.READ_USER + "')")
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
    return userService
        .getUserById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PreAuthorize("hasAuthority('" + Permissions.CREATE_USER + "')")
  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
    UserResponseDTO createdUser = userService.createUser(user);
    return ResponseEntity.status(201).body(createdUser);
  }

  @PreAuthorize("hasAuthority('" + Permissions.CREATE_USERS + "')")
  @PostMapping("/batch")
  public ResponseEntity<List<UserResponseDTO>> createUsers(@RequestBody List<UserRequestDTO> users) {
    List<UserResponseDTO> createdUsers = userService.createUsers(users);
    return ResponseEntity.status(201).body(createdUsers);
  }

  @PreAuthorize("hasAuthority('" + Permissions.UPDATE_USER + "')")
  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable UUID id, @RequestBody UserRequestDTO userDetails) {
    return userService
        .updateUser(id, userDetails)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PreAuthorize("hasAuthority('" + Permissions.UPDATE_USERS + "')")
  @PutMapping("/batch")
  public ResponseEntity<List<UserResponseDTO>> updateUsers(@RequestBody List<UserRequestDTO> users) {
    List<UserResponseDTO> updateUsers = userService.updateUsers(users);
    return ResponseEntity.ok(updateUsers);
  }

  @PreAuthorize("hasAuthority('" + Permissions.DELETE_USER + "')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
    boolean deleted = userService.deleteUser(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
