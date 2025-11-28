/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Optional<UserDetails> findByUsername(String username);
    Optional<User> findByUsernameAndIdNot(String username, UUID id);
    Optional<User> findByEmailAndIdNot(String email, UUID id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query(value = """
            SELECT u FROM User u
            WHERE u.username LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<User> findByUserContainingIgnoreCase(Specification<User> specification, String search, Pageable pageable);
}
