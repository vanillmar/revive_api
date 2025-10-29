/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;
import com.example.revive_app.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByActiveTrue();
    boolean existsByUserId(UUID id);
    Optional<Person> findByUserId(UUID id);
}
