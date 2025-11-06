/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository;

import com.example.revive_app.model.Address;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryCustom {
    private final AddressRepository addressRepository;

    public Optional<Address> findPrimaryAddressByUserId(UUID userId) {
        return addressRepository.findPrimaryAddressByUserId(userId);
    }

    public List<Address> findAllAddressesByUserId(UUID userId) {
        return addressRepository.findAllAddressesByUserId(userId);
    }
}