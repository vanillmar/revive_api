/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.mapper.AddressMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.Address;
import com.example.revive_app.repository.AddressRepository;
import com.example.revive_app.repository.AddressRepositoryCustom;
import com.example.revive_app.request.AddressRequestDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressRepositoryCustom addressRepositoryCustom;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressRepositoryCustom addressRepositoryCustom,
            AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressRepositoryCustom = addressRepositoryCustom;
        this.addressMapper = addressMapper;
    }

    public Optional<Address> getById(Long id) {
        return addressRepository.findById(id);
    }

    public Optional<Address> getPrimaryAddressByPersonId(Long id) {
        return addressRepository.findPrimaryAddressByPersonId(id);
    }

    public Optional<Address> getPrimaryAddressByUserId(UUID id) {
        return addressRepositoryCustom.findPrimaryAddressByUserId(id);
    }

    public List<Address> getAllAddressesByUserId(UUID userId) {
        return addressRepositoryCustom.findAllAddressesByUserId(userId);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address create(AddressRequestDTO dto) {
        Address address = addressMapper.toEntity(dto);
        return addressRepository.save(address);
    }

    public Address update(Long id, AddressRequestDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("Address ID cannot be null");
        }

        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));

        Address incoming = addressMapper.toEntity(dto);

        existing.setStreet(incoming.getStreet());
        existing.setCity(incoming.getCity());
        existing.setState(incoming.getState());
        existing.setZipCode(incoming.getZipCode());
        existing.setCountry(incoming.getCountry());
        existing.setPerson(incoming.getPerson());
        existing.setUpdatedBy(incoming.getUpdatedBy());
        existing.setUpdatedAt(LocalDateTime.now());

        return addressRepository.save(existing);
    }

    public boolean delete(Long id) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.delete(address);
            return true;
        }).orElse(false);
    }

}
