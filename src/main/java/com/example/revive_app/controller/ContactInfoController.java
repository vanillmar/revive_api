/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.ContactInfoRequestDTO;
import com.example.revive_app.data.dto.ContactInfoResponseDTO;
import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.mapper.ContactInfoMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.ContactInfo;
import com.example.revive_app.service.ContactInfoService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;
    private final ContactInfoMapper contactInfoMapper;

    public ContactInfoController(ContactInfoService contactInfoService, ContactInfoMapper contactInfoMapper) {
        this.contactInfoService = contactInfoService;
        this.contactInfoMapper = contactInfoMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ContactInfoResponseDTO>> getById(@PathVariable Long id) {
        ContactInfo contactInfo = contactInfoService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the ContactInfo"));
        ContactInfoResponseDTO contactInfoResponse = contactInfoMapper.toResponse(contactInfo);
        ResponseDTO<ContactInfoResponseDTO> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("ContactInfo fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<ResponseDTO<ContactInfoResponseDTO>> getByPersonId(@PathVariable Long id) {
        ContactInfo contactInfo = contactInfoService.getByPersonId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the ContactInfo"));
        ContactInfoResponseDTO contactInfoResponse = contactInfoMapper.toResponse(contactInfo);
        ResponseDTO<ContactInfoResponseDTO> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("ContactInfo fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/primary/user/{id}")
    public ResponseEntity<ResponseDTO<ContactInfoResponseDTO>> getPrimaryByUserId(@PathVariable UUID id) {
        ContactInfo contactInfo = contactInfoService.getPrimaryByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the ContactInfo"));
        ContactInfoResponseDTO contactInfoResponse = contactInfoMapper.toResponse(contactInfo);
        ResponseDTO<ContactInfoResponseDTO> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("ContactInfo fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDTO<List<ContactInfoResponseDTO>>> getAllByUserId(@PathVariable UUID id) {
        List<ContactInfo> contactInfos = contactInfoService.getAllByUserId(id);
        List<ContactInfoResponseDTO> contactInfoResponse = contactInfoMapper.toListResponse(contactInfos);
        ResponseDTO<List<ContactInfoResponseDTO>> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("ContactInfo fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ContactInfoResponseDTO>>> getAll() {
        List<ContactInfo> contactInfos = contactInfoService.getAll();
        List<ContactInfoResponseDTO> contactInfoResponse = contactInfoMapper.toListResponse(contactInfos);
        ResponseDTO<List<ContactInfoResponseDTO>> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("ContactInfo fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ResponseDTO<ContactInfoResponseDTO>> create(@RequestBody ContactInfoRequestDTO dto) {
        ContactInfo contactInfo = contactInfoService.create(dto);
        ContactInfoResponseDTO contactInfoResponse = contactInfoMapper.toResponse(contactInfo);
        ResponseDTO<ContactInfoResponseDTO> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("ContactInfo created successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<ResponseDTO<List<ContactInfo>>> bulkInsertContacts(
            @RequestBody List<ContactInfoRequestDTO> dtos) {
        List<ContactInfo> contacts = contactInfoMapper.toListEntity(dtos);
        List<ContactInfo> savedContacts = contactInfoService.bulkInsertContacts(contacts);
        ResponseDTO<List<ContactInfo>> response = new ResponseDTO<>();
        response.setData(savedContacts);
        response.setMessage("Contacts created successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        response.setTotal(savedContacts.size());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<ContactInfoResponseDTO>> update(@PathVariable Long id,
            @RequestBody ContactInfoRequestDTO dto) {
        ContactInfo contactInfo = contactInfoService.update(id, dto);
        ContactInfoResponseDTO contactInfoResponse = contactInfoMapper.toResponse(contactInfo);
        ResponseDTO<ContactInfoResponseDTO> response = new ResponseDTO<>();
        response.setData(contactInfoResponse);
        response.setMessage("Contact updated successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        boolean deleted = contactInfoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
