/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.service;

import com.example.revive_app.model.Role;
import com.example.revive_app.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return new HashSet<>(roles);
    }
}
