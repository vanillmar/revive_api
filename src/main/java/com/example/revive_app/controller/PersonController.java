/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.controller;

import com.example.revive_app.data.dto.PersonRequestDTO;
import com.example.revive_app.data.dto.PersonResponseDTO;
import com.example.revive_app.data.dto.ResponseDTO;
import com.example.revive_app.data.mapper.PersonMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.Person;
import com.example.revive_app.service.PersonService;
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
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<PersonResponseDTO>> getById(@PathVariable Long id) {
        Person person = personService.getPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the person"));
        PersonResponseDTO personResponse = personMapper.toResponse(person);
        ResponseDTO<PersonResponseDTO> response = new ResponseDTO<>();
        response.setData(personResponse);
        response.setMessage("Person fetched successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDTO<PersonResponseDTO>> getByUserId(@PathVariable UUID id) {
        Person person = personService.getPersonByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch the person"));
        PersonResponseDTO personResponse = personMapper.toResponse(person);
        ResponseDTO<PersonResponseDTO> response = new ResponseDTO<>();
        response.setData(personResponse);
        response.setMessage("Person fetched successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<PersonResponseDTO>>> getAllPersons() {
        List<PersonResponseDTO> persons = personMapper.toListResponse(personService.getAllPersons());
        ResponseDTO<List<PersonResponseDTO>> response = new ResponseDTO<>();
        response.setData(persons);
        response.setMessage("Persons fetched successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<PersonResponseDTO>> create(@RequestBody PersonRequestDTO dto) {
        Person person = personMapper.toEntity(dto);
        Person personSaved = personService.create(person);
        PersonResponseDTO personResponse = personMapper.toResponse(personSaved);
        ResponseDTO<PersonResponseDTO> response = new ResponseDTO<>();
        response.setData(personResponse);
        response.setMessage("Person created successfully.");
        response.setStatus(HttpStatus.CREATED.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<PersonResponseDTO>> update(@PathVariable Long id,
            @RequestBody PersonRequestDTO dto) {
        Person person = personService.update(id, dto);
        PersonResponseDTO personResponse = personMapper.toResponse(person);
        ResponseDTO<PersonResponseDTO> response = new ResponseDTO<>();
        response.setData(personResponse);
        response.setMessage("Person updated successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        boolean deleted = personService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
