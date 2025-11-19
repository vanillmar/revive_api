/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.data.dto.PersonRequestDTO;
import com.example.revive_app.data.mapper.PersonMapper;
import com.example.revive_app.exception.ResourceNotFoundException;
import com.example.revive_app.model.Person;
import com.example.revive_app.repository.PersonRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }
    public Optional<Person> getPersonById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Person ID cannot be null");
        if (!personRepository.existsById(id))
            throw new ResourceNotFoundException("Person not found with ID: " + id);

        return personRepository.findById(id);
    }

    public Optional<Person> getPersonByUserId(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("Person ID cannot be null");
        if (!personRepository.existsByUserId(id))
            throw new ResourceNotFoundException("Person not found with User ID: " + id);

        return personRepository.findByUserId(id);
    }

    public List<Person> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        if (persons.isEmpty())
            throw new ResourceNotFoundException("No persons found");
        return persons;
    }

    public Person create(PersonRequestDTO dto) {
        Person person = personMapper.toEntity(dto);
        return personRepository.save(person);
    }

    public Person update(Long id, PersonRequestDTO dto) {
        if (id == null)
            throw new IllegalArgumentException("Person ID cannot be null");
        Person existing = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with User ID: " + id));
        Person incoming = personMapper.toEntity(dto);
        existing.setBio(incoming.getBio());
        existing.setFirstName(incoming.getFirstName());
        existing.setLastName(incoming.getLastName());
        existing.setGender(incoming.getGender());
        existing.setMaritalStatus(incoming.getMaritalStatus());
        existing.setBirthDate(incoming.getBirthDate());
        existing.setBio(incoming.getBio());
        existing.setNationalId(incoming.getNationalId());
        existing.setUpdatedBy(incoming.getUpdatedBy());
        existing.setUpdatedAt(LocalDateTime.now());
        return personRepository.save(existing);
    }

    public boolean delete(Long id) {
        return personRepository.findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElse(false);
    }
}
