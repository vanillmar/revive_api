/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Address;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a JOIN a.person p JOIN User u ON u.person.id = p.id WHERE u.id = :userId")
    List<Address> findAllAddressesByUserId(@Param("userId") UUID userId);

    @Query("SELECT a FROM Address a JOIN a.person p JOIN User u ON u.person.id = p.id WHERE u.id = :userId AND a.isPrimary = true")
    Optional<Address> findPrimaryAddressByUserId(@Param("userId") UUID userId);

    @Query("SELECT a FROM Address a JOIN a.person p WHERE p.id = :personId AND a.isPrimary = true")
    Optional<Address> findPrimaryAddressByPersonId(@Param("personId") Long personId);
}
