/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.exception.EmailAlreadyExistsException;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.exception.UsernameAlreadyExistsException;
import com.example.revive_app.model.User;
import com.example.revive_app.repository.UserRepository;
import com.example.revive_app.repository.specifications.UserSpecifications;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        List<User> users = userRepository.findAll(UserSpecifications.isNotDeleted());
        if (users.isEmpty())
            throw new ResourceNotFoundException("No users found");
        return users;
    }

    public Optional<User> getUserById(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("User ID cannot be null");
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("User not found with ID: " + id);

        return userRepository.findById(id);
    }

    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Transactional
    public User update(UUID id, User incoming) {
        if (id == null)
            throw new IllegalArgumentException("User ID cannot be null");
        // 1. Retrieve the existing user
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        // 2. Check if the new username is already taken by another user
        if (userRepository.findByUsernameAndIdNot(incoming.getUsername(), id).isPresent()) {
            throw new UsernameAlreadyExistsException("Username '" + incoming.getUsername() + "' is already taken.");
        }
        if (userRepository.findByEmailAndIdNot(incoming.getEmail(), id).isPresent()) {
            throw new EmailAlreadyExistsException("Email '" + incoming.getEmail() + "' is already taken.");
        }
        existing.setUsername(incoming.getUsername());
        // if (incoming.getPassword()!= null || !incoming.getPassword().isEmpty())
        // existing.setPassword(passwordEncoder.encode(incoming.getPassword()));
        existing.setEmail(incoming.getEmail());
        if (incoming.getRoles() != null && !incoming.getRoles().isEmpty())
            existing.setRoles(incoming.getRoles());
        if (incoming.isEnabled())
            existing.setEnabled(incoming.isEnabled());
        existing.setNotifications(incoming.isNotifications());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy(incoming.getUpdatedBy());
        existing.setActive(incoming.getActive());
        // 3. Update the username and save
        return userRepository.save(existing);
    }

    public List<User> updateUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Transactional
    public boolean deleteById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setDeleted(currentUser);
        user.setEnabled(false);
        return userRepository.save(user) != null;
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

    public Page<User> findAllWithFilters(String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            // Combine specifications
            Specification<User> spec = UserSpecifications.isNotDeleted().and(UserSpecifications.searchByUser(search));

            return userRepository.findAll(spec, pageable);
        }
        return userRepository.findByUserContainingIgnoreCase(UserSpecifications.isNotDeleted(), search, pageable);
    }

}