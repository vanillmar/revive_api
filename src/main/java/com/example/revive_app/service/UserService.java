package com.example.revive_app.service;

import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.User;
import com.example.revive_app.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserResponseDTO getMe(Object principal) {
    User user = (User) principal;
    if (user == null) throw new ResourceNotFoundException("User not Authenticated"); 
    return toDto(user);
  }

  public List<UserResponseDTO> getAllUsers() {
    List<UserResponseDTO> users = userRepository.findAll().stream().map(this::toDto).toList();
    if (users.isEmpty()) throw new ResourceNotFoundException("No users found");
    return users;
  }

  public Optional<UserResponseDTO> getUserById(UUID id) {
    if (id == null) throw new IllegalArgumentException("User ID cannot be null");
    if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User not found with ID: " + id);

    return userRepository.findById(id).map(this::toDto);
  }

  public UserResponseDTO createUser(UserRequestDTO user) {
    return toDto(userRepository.save(toEntity(user)));
  }

  public List<UserResponseDTO> createUsers(List<UserRequestDTO> users) {
    return userRepository.saveAll(users.stream().map(this::toEntity).toList()).stream()
        .map(this::toDto)
        .toList();
  }

  public Optional<UserResponseDTO> updateUser(UUID id, UserRequestDTO userDetails) {
    return userRepository
        .findById(id)
        .map(
            user -> {
              user.setUsername(userDetails.getUsername());
              user.setEmail(userDetails.getEmail());
              user.setEnabled(userDetails.isEnabled());
              user.setRoles(userDetails.getRoles());
              if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
              }
              return toDto(userRepository.save(user));
            });
  }

  public List<UserResponseDTO> updateUsers(List<UserRequestDTO> users) {
    return userRepository.saveAll(users.stream().map(this::toEntity).toList()).stream()
        .map(this::toDto)
        .toList();
  }

  public boolean deleteUser(UUID id) {
    return userRepository
        .findById(id)
        .map(
            user -> {
              userRepository.delete(user);
              return true;
            })
        .orElse(false);
  }

  private UserResponseDTO toDto(User user) {
    return UserResponseDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .enabled(user.isEnabled())
        .roles(user.getRoles())
        .build();
  }

  private User toEntity(UserRequestDTO userRequest) {
    User user =
        new User(
            userRequest.getUsername(),
            passwordEncoder.encode(userRequest.getPassword()),
            userRequest.getEmail());
    user.setRoles(userRequest.getRoles());
    return user;
  }
}
