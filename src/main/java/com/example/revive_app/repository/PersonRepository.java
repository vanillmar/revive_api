/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;
import com.example.revive_app.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByActiveTrue();

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END "
            + "FROM User u WHERE u.id = :userId AND u.person IS NOT NULL")
    boolean existsByUserId(@Param("userId") UUID userId);

    @Query("SELECT u.person FROM User u WHERE u.id = :userId")
    Optional<Person> findByUserId(@Param("userId") UUID userId);
}
