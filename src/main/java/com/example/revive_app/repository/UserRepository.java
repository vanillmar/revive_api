/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByUsername(String username);
    Optional<User> findByUsernameAndIdNot(String username, UUID id);
    Optional<User> findByEmailAndIdNot(String email, UUID id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
