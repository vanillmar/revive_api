package com.example.revive_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.revive_app.model.Address;
import com.example.revive_app.repository.AddressRepository;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Get all addresses
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get address by id
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new address
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    // Create multiple addresses
    @PostMapping("/batch")
    public List<Address> createAddresses(@RequestBody List<Address> addresses) {
        return addressRepository.saveAll(addresses);
    }

    // Update address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        return addressRepository.findById(id)
                .map(address -> {
                    address.setStreet(addressDetails.getStreet());
                    address.setCity(addressDetails.getCity());
                    address.setState(addressDetails.getState());
                    address.setZipCode(addressDetails.getZipCode());
                    Address updated = addressRepository.save(address);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update multiple addresses
    @PutMapping("/batch")
    public List<Address> updateAddresses(@RequestBody List<Address> addresses) {
        return addressRepository.saveAll(addresses);
    }

    // Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long id) {
        return addressRepository.findById(id)
                .map(address -> {
                    addressRepository.delete(address);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
