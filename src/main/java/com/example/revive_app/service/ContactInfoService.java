/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.dto.ContactInfoRequestDTO;
import com.example.revive_app.data.mapper.ContactInfoMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.ContactInfo;
import com.example.revive_app.repository.ContactInfoRepository;
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

    public Optional<ContactInfo> getPrimaryByUserId(UUID id) {
        return contactInfoRepository.findPrimaryContactInfoByUserId(id);
    }

    public List<ContactInfo> getAllByUserId(UUID id) {
        return contactInfoRepository.findAllContactInfoByUserId(id);
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
        ContactInfo existing = contactInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContactInfo not found with User ID: " + id));
        ContactInfo incoming = contactInfoMapper.toEntity(dto);
        existing.setEmail(incoming.getEmail());
        existing.setAlternateEmail(incoming.getAlternateEmail());
        existing.setPrimary(incoming.isPrimary());
        existing.setAlternateEmail(incoming.getAlternateEmail());
        existing.setEmergencyContactName(incoming.getEmergencyContactName());
        existing.setEmergencyContactPhone(incoming.getEmergencyContactPhone());
        existing.setPerson(incoming.getPerson());
        existing.setActive(incoming.getActive());
        existing.setUpdatedBy(incoming.getUpdatedBy());
        existing.setUpdatedAt(incoming.getUpdatedAt());
        return contactInfoRepository.save(existing);
    }

    public boolean delete(Long id) {
        return contactInfoRepository.findById(id).map(contactInfo -> {
            contactInfoRepository.delete(contactInfo);
            return true;
        }).orElse(false);
    }

}
