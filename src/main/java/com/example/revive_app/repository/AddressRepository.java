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

    Optional<Address> findByPersonIdAndIsPrimaryTrue(Long id);
    // You can add custom query methods here if needed

    // Query through the User -> Person -> Address relationship
    @Query("SELECT a FROM Address a " + "WHERE a.person.id = (SELECT u.person.id FROM User u WHERE u.id = :userId) "
            + "AND a.isPrimary = true")
    Optional<Address> findPrimaryAddressByUserId(@Param("userId") UUID userId);

    // Alternative: More explicit join syntax
    @Query("SELECT a FROM Address a " + "JOIN a.person p " + "JOIN User u ON u.person.id = p.id "
            + "WHERE u.id = :userId AND a.isPrimary = true")
    Optional<Address> findPrimaryAddressByUserIdWithJoin(@Param("userId") UUID userId);

    // If you also want to get all addresses for a user
    @Query("SELECT a FROM Address a " + "WHERE a.person.id = (SELECT u.person.id FROM User u WHERE u.id = :userId)")
    List<Address> findAllAddressesByUserId(@Param("userId") UUID userId);
}
