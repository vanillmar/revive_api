package com.example.revive_app.data.mapper;

import com.example.revive_app.data.dto.AddressRequestDTO;
import com.example.revive_app.data.dto.AddressResponseDTO;
import com.example.revive_app.model.Address;
import com.example.revive_app.model.Employee;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface AddressMapper {

  @Mapping(target = "employeeId", source = "employee.id")
  AddressResponseDTO toResponseDTO(Address address);

  List<AddressResponseDTO> toAddressResponseDTOList(List<Address> addresses);

  @Mapping(target = "employee", expression = "java(employeeFromId(dto.getEmployeeId()))")
  Address toEntity(AddressRequestDTO dto);

  List<Address> toListEntitiy(List<AddressRequestDTO> dtos);

  default Employee employeeFromId(java.util.UUID id) {
    if (id == null) {
        return null;
    }
    Employee e = new Employee();
    e.setId(id);
    return e;
  }
}
