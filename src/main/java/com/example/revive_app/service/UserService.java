/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.dto.UserRequestDTO;
import com.example.revive_app.data.dto.UserResponseDTO;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.User;
import com.example.revive_app.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.upload.dir}")
    private String uploadDir;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getMe(Object principal) {
        User user = (User) principal;
        if (user == null)
            throw new ResourceNotFoundException("User not Authenticated");
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            throw new ResourceNotFoundException("No users found");
        return users;
    }

    public Optional<UserResponseDTO> getUserById(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("User ID cannot be null");
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("User not found with ID: " + id);

        return userRepository.findById(id).map(this::toDto);
    }

    public User createUser(UserRequestDTO user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        return userRepository.save(toEntity(user));
    }

    public List<User> createUsers(List<UserRequestDTO> usersDTO) {
        return userRepository.saveAll(usersDTO.stream().map(this::toEntity).toList());
    }

    public User updateUser(UUID id, UserRequestDTO userDetails) {
        if (id == null)
            throw new IllegalArgumentException("User ID cannot be null");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        user.setEnabled(userDetails.isEnabled());
        return userRepository.save(user);

    }

    public List<User> updateUsers(List<UserRequestDTO> users) {
        return userRepository.saveAll(users.stream().map(this::toEntity).toList());
    }

    public boolean deleteUser(UUID id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    private UserResponseDTO toDto(User user) {
        return UserResponseDTO.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail())
                .enabled(user.isEnabled()).roles(user.getRoles()).build();
    }

    private User toEntity(UserRequestDTO userRequest) {
        User user = new User(userRequest.getUsername(), passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getEmail());
        user.setRoles(userRequest.getRoles());
        return user;
    }

    public Long getTotalUsers() {
        return userRepository.count();
    }

    public Long getTotalActiveUsers() {
        User probe = new User();
        probe.setActive(true);

        Example<User> example = Example.of(probe);

        return userRepository.count(example);
    }

    public Long getTotalInactiveUsers() {
        User probe = new User();
        probe.setActive(false);

        Example<User> example = Example.of(probe);

        return userRepository.count(example);
    }

    public String saveAvatar(UUID id, MultipartFile file) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath);

            String url = "/uploads/profile-pictures/" + fileName;
            user.setAvatar(url);
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
            
            return url;
        } catch (IOException e) {
            throw new java.io.UncheckedIOException("Error saving file", e);
        }

    }

}
