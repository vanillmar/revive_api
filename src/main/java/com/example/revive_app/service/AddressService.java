package com.example.revive_app.service;

import com.example.revive_app.data.dto.AddressRequestDTO;
import com.example.revive_app.data.dto.AddressResponseDTO;
import com.example.revive_app.model.Address;
import com.example.revive_app.model.Employee;
import com.example.revive_app.repository.AddressRepository;
import com.example.revive_app.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.revive_app.data.mapper.AddressMapper;

@Service
public class AddressService {

  private static final String EMPLOYEE_NOT_FOUND_WITH_ID = "Employee not found with id: ";
    private static final String ADDRESS_NOT_FOUND_WITH_ID = "Address not found with id: ";

  private final AddressRepository addressRepository;
  private final EmployeeRepository employeeRepository;
  private final AddressMapper addressMapper;

  @Autowired
  public AddressService(
      AddressRepository addressRepository, EmployeeRepository employeeRepository, AddressMapper addressMapper) {
    this.addressRepository = addressRepository;
    this.employeeRepository = employeeRepository;
    this.addressMapper = addressMapper;
  }

  public List<AddressResponseDTO> getAllAddresses() {
    return addressMapper.toAddressResponseDTOList(addressRepository.findAll());
  }

  public Optional<AddressResponseDTO> getAddressById(Long id) {
    return addressRepository
        .findById(id)
        .map(addressMapper::toResponseDTO)
        .or(() -> Optional.empty());
  }

  public AddressResponseDTO createAddress(AddressRequestDTO dto) {
    Employee employee = employeeRepository
        .findById(dto.getEmployeeId())
        .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND_WITH_ID + dto.getEmployeeId()));
    Address addressEntity = addressMapper.toEntity(dto);
    addressEntity.setEmployee(employee);
    Address savedAddress = addressRepository.save(addressEntity);
    return addressMapper.toResponseDTO(savedAddress);
  }

  public List<AddressResponseDTO> createAddresses(List<AddressRequestDTO> addresses) {
    List<Address> addressEntities = addressMapper.toListEntitiy(addresses);
    List<Address> savedAddresses = addressRepository.saveAll(addressEntities);
    return addressMapper.toAddressResponseDTOList(savedAddresses);
  }

public AddressResponseDTO updateAddress(Long id, AddressRequestDTO dto) {
    // Validate input DTO
    if (dto == null) {
        throw new IllegalArgumentException("AddressRequestDTO cannot be null");
    }

    // Fetch the existing address
    Address existingAddress = addressRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException(ADDRESS_NOT_FOUND_WITH_ID + id));

    // Update the fields of the existing address with the new values
    existingAddress.setStreet(dto.getStreet());
    existingAddress.setCity(dto.getCity());
    existingAddress.setState(dto.getState());
    existingAddress.setZipCode(dto.getZipCode());
    // Save the updated address
    Address savedAddress = addressRepository.save(existingAddress);

    // Return the updated address as a response DTO
    return addressMapper.toResponseDTO(savedAddress);
}

  public List<AddressResponseDTO> updateAddresses(List<AddressRequestDTO> addresses) {
    List<Address> addressEntities = addressMapper.toListEntitiy(addresses);
    List<Address> savedAddresses = addressRepository.saveAll(addressEntities);
    return addressMapper.toAddressResponseDTOList(savedAddresses);    
  }

  public boolean deleteAddress(Long id) {
    return addressRepository
        .findById(id)
        .map(
            address -> {
              addressRepository.delete(address);
              return true;
            })
        .orElse(false);
  }
}
