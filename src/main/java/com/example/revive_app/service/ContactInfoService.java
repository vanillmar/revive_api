/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.mapper.ContactInfoMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.ContactInfo;
import com.example.revive_app.repository.ContactInfoRepository;
import com.example.revive_app.request.ContactInfoRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;

    public ContactInfoService(ContactInfoRepository contactInfoRepository, ContactInfoMapper contactInfoMapper) {
        this.contactInfoRepository = contactInfoRepository;
        this.contactInfoMapper = contactInfoMapper;
    }

    public Optional<ContactInfo> getById(Long id) {
        return contactInfoRepository.findById(id);
    }

    public Optional<ContactInfo> getByPersonId(Long id) {
        return contactInfoRepository.findByPersonId(id);
    }
    public Optional<ContactInfo> getByUserId(UUID id) {
        return contactInfoRepository.findByUserId(id);
    }


    public List<ContactInfo> getAll() {
        return contactInfoRepository.findAll();
    }

    public ContactInfo create(ContactInfoRequestDTO dto) {
        ContactInfo contactInfo = contactInfoMapper.toEntity(dto);
        return contactInfoRepository.save(contactInfo);
    }

    public ContactInfo update(Long id, ContactInfoRequestDTO dto) {
        if (id == null)
            throw new IllegalArgumentException("ContactInfo ID cannot be null");
        if (!contactInfoRepository.existsById(id))
            throw new ResourceNotFoundException("ContactInfo not found with User ID: " + id);
        ContactInfo contactInfo = contactInfoMapper.toEntity(dto);
        return contactInfoRepository.save(contactInfo);
    }

    public boolean delete(Long id) {
        return contactInfoRepository.findById(id).map(contactInfo -> {
            contactInfoRepository.delete(contactInfo);
            return true;
        }).orElse(false);
    }
}
