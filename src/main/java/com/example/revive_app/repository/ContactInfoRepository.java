/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.ContactInfo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    @Query("SELECT a FROM ContactInfo a WHERE a.person.id = (SELECT u.person.id FROM User u WHERE u.id = :userId) AND a.primary = true")
    Optional<ContactInfo> findPrimaryContactInfoByUserId(@Param("userId") UUID userId);

    @Query("SELECT a FROM ContactInfo a WHERE a.person.id = (SELECT u.person.id FROM User u WHERE u.id = :userId)")
    List<ContactInfo> findAllContactInfoByUserId(@Param("userId") UUID userId);

    @Query("SELECT a FROM Address a WHERE a.person.id = :id ")
    Optional<ContactInfo> findByPersonId(Long id);
}
