/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.mapper.AddressMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.Address;
import com.example.revive_app.request.AddressRequestDTO;
import com.example.revive_app.response.AddressResponseDTO;
import com.example.revive_app.service.AddressService;
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
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> getById(@PathVariable Long id) {
        Address address = addressService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the address"));
        AddressResponseDTO addressResponse = addressMapper.toResponse(address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(addressResponse);
        response.setMessage("Address fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> getPrimaryAddressByPersonId(@PathVariable Long id) {
        Address address = addressService.getPrimaryAddressByPersonId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the address"));
        AddressResponseDTO addressResponse = addressMapper.toResponse(address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(addressResponse);
        response.setMessage("Address fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDTO<List<AddressResponseDTO>>> getAllAddressByUserId(@PathVariable UUID id) {
        List<Address> addresses = addressService.getAllAddressesByUserId(id);
        List<AddressResponseDTO> addressResponse = addressMapper.toResponseDTOs(addresses);
        ResponseDTO<List<AddressResponseDTO>> response = new ResponseDTO<>();
        response.setData(addressResponse);
        response.setMessage("Address fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/primary/user/{id}")
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> getPrimaryAddressByUserId(@PathVariable UUID id) {
        Address address = addressService.getPrimaryAddressByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the address"));
        AddressResponseDTO addressResponse = addressMapper.toResponse(address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(addressResponse);
        response.setMessage("Address fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<AddressResponseDTO>>> getAll() {
        List<AddressResponseDTO> addresses = addressMapper.toResponseDTOs(addressService.getAll());
        ResponseDTO<List<AddressResponseDTO>> response = new ResponseDTO<>();
        response.setData(addresses);
        response.setMessage("Addresses fetched successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> create(@RequestBody AddressRequestDTO dto) {
        AddressResponseDTO addressResponse = addressMapper.toResponse(addressService.create(dto));
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(addressResponse);
        response.setMessage("Address created successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<AddressResponseDTO>> update(@PathVariable Long id,
            @RequestBody AddressRequestDTO dto) {
        Address address = addressService.update(id, dto);
        AddressResponseDTO addressResponse = addressMapper.toResponse(address);
        ResponseDTO<AddressResponseDTO> response = new ResponseDTO<>();
        response.setData(addressResponse);
        response.setMessage("Address updated successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        boolean deleted = addressService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
