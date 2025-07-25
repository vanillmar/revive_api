package com.example.revive_app.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import com.example.revive_app.model.User;
import com.example.revive_app.repository.UserRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get all users
    @PreAuthorize("hasAuthority('READ_ME')")
    @GetMapping("/me")
    public ResponseEntity<User> getMe(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    // Get all users
    @PreAuthorize("hasAuthority('READ_USERS')")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by id
    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new user
    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // Encode password
        return userRepository.save(user);
    }

    // Create multiple users
    @PreAuthorize("hasAuthority('CREATE_USERS')")    
    @PostMapping("/batch")
    public List<User> createUsers(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

    // Update user
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    User updated = userRepository.save(user);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update multiple users
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @PutMapping("/batch")
    public List<User> updateUsers(@RequestBody List<User> users) {
        return userRepository.saveAll(users);
    }

    // Delete user
    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
