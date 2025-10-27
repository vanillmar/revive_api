/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;
import com.example.revive_app.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByActiveTrue();
}
