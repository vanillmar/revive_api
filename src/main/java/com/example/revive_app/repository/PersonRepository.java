package com.example.revive_app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.revive_app.model.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByActiveTrue();
}
