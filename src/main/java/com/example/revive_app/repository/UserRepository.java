package com.example.revive_app.repository;

import com.example.revive_app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByUsername(String username);
}