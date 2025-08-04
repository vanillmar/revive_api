package com.example.revive_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.revive_app.data.Permissions;
import com.example.revive_app.data.dto.AddressRequestDTO;
import com.example.revive_app.data.dto.AddressResponseDTO;
import com.example.revive_app.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PreAuthorize("hasAuthority('"+ Permissions.READ_ADDRESSES +"')")
    @GetMapping
    public List<AddressResponseDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PreAuthorize("hasAuthority('" + Permissions.READ_ADDRESS + "')")
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressResponseDTO> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_ADDRESS + "')")
    @PostMapping
    public AddressResponseDTO createAddress(@RequestBody AddressRequestDTO address) {
        return addressService.createAddress(address);
    }

    @PreAuthorize("hasAuthority('" + Permissions.CREATE_ADDRESSES + "')")
    @PostMapping("/batch")
    public List<AddressResponseDTO> createAddresses(@RequestBody List<AddressRequestDTO> addresses) {
        return addressService.createAddresses(addresses);
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_ADDRESS + "')")
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO addressDetails) {
        Optional<AddressResponseDTO> updated = addressService.updateAddress(id, addressDetails);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_ADDRESSES + "')")
    @PutMapping("/batch")
    public List<AddressResponseDTO> updateAddresses(@RequestBody List<AddressRequestDTO> addresses) {
        return addressService.updateAddresses(addresses);
    }

    @PreAuthorize("hasAuthority('" + Permissions.DELETE_ADDRESS + "')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long id) {
        boolean deleted = addressService.deleteAddress(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
