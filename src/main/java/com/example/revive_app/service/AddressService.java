/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.mapper.AddressMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.Address;
import com.example.revive_app.repository.AddressRepository;
import com.example.revive_app.request.AddressRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Optional<Address> getById(Long id) {
        return addressRepository.findById(id);
    }

    public Optional<Address> getPrimaryAddressByPersonId(Long id) {
        return addressRepository.findByPersonIdAndIsPrimaryTrue(id);
    }

    public Optional<Address> getPrimaryAddressByUserId(UUID id) {
        return addressRepository.findPrimaryAddressByUserId(id);
    }

    public List<Address> getAllAddressesByUserId(UUID userId) {
        return addressRepository.findAllAddressesByUserId(userId);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address create(AddressRequestDTO dto) {
        Address address = addressMapper.toEntity(dto);
        return addressRepository.save(address);
    }

    public Address update(Long id, AddressRequestDTO dto) {
        if (id == null)
            throw new IllegalArgumentException("Address ID cannot be null");
        if (!addressRepository.existsById(id))
            throw new ResourceNotFoundException("Address not found with User ID: " + id);
        Address address = addressMapper.toEntity(dto);
        return addressRepository.save(address);
    }

    public boolean delete(Long id) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.delete(address);
            return true;
        }).orElse(false);
    }

}
