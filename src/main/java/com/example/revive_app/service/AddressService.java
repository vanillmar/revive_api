package com.example.revive_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.revive_app.data.dto.AddressRequestDTO;
import com.example.revive_app.data.dto.AddressResponseDTO;
import com.example.revive_app.model.Address;
import com.example.revive_app.model.Employee;
import com.example.revive_app.repository.AddressRepository;
import com.example.revive_app.repository.EmployeeRepository;

@Service
public class AddressService {

    private static final String EMPLOYEE_NOT_FOUND_WITH_ID = "Employee not found with id: ";
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<AddressResponseDTO> getAddressById(Long id) {
        return addressRepository.findById(id).map(this::toDto);
    }

    public AddressResponseDTO createAddress(AddressRequestDTO address) {
        Employee employee = employeeRepository.findById(address.getEmployeeId())
                .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND_WITH_ID + address.getEmployeeId()));
        Address newAddress = new Address();
        newAddress.setStreet(address.getStreet());
        newAddress.setCity(address.getCity());
        newAddress.setState(address.getState());
        newAddress.setZipCode(address.getZipCode());
        newAddress.setEmployee(employee);

        return toDto(addressRepository.save(newAddress));
    }

    public List<AddressResponseDTO> createAddresses(List<AddressRequestDTO> addresses) {
        List<Address> newAddresses = addresses.stream()
                .map(address -> {
                    Employee employee = employeeRepository.findById(address.getEmployeeId())
                            .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND_WITH_ID + address.getEmployeeId()));
                    Address newAddress = new Address();
                    newAddress.setStreet(address.getStreet());
                    newAddress.setCity(address.getCity());
                    newAddress.setState(address.getState());
                    newAddress.setZipCode(address.getZipCode());
                    newAddress.setEmployee(employee);
                    return newAddress;
                })
                .toList();
        return addressRepository.saveAll(newAddresses).stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<AddressResponseDTO> updateAddress(Long id, AddressRequestDTO addressDetails) {
        return addressRepository.findById(id).map(existingAddress -> {
            existingAddress.setStreet(addressDetails.getStreet());
            existingAddress.setCity(addressDetails.getCity());
            existingAddress.setState(addressDetails.getState());
            existingAddress.setZipCode(addressDetails.getZipCode());
            Employee employee = employeeRepository.findById(addressDetails.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND_WITH_ID + addressDetails.getEmployeeId()));
            existingAddress.setEmployee(employee);
            return toDto(addressRepository.save(existingAddress));
        });
    }

    public List<AddressResponseDTO> updateAddresses(List<AddressRequestDTO> addresses) {
        List<Address> newAddresses =  addresses.stream()
                .map(address -> {
                    Address existingAddress = new Address();
                    existingAddress.setId(address.getId());
                    existingAddress.setStreet(address.getStreet());
                    existingAddress.setCity(address.getCity());
                    existingAddress.setState(address.getState());
                    existingAddress.setZipCode(address.getZipCode());
                    Employee employee = employeeRepository.findById(address.getEmployeeId())
                            .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND_WITH_ID + address.getEmployeeId()));
                    existingAddress.setEmployee(employee);
                    return existingAddress;
                })
                .toList();
        return addressRepository.saveAll(newAddresses).stream()
                .map(this::toDto)
                .toList();
    }

    public boolean deleteAddress(Long id) {
        return addressRepository.findById(id)
                .map(address -> {
                    addressRepository.delete(address);
                    return true;
                })
                .orElse(false);
    }

    private AddressResponseDTO toDto(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .employeeId(address.getEmployee().getId())
                .build();
    }
}
