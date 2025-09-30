package com.example.revive_app.service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.revive_app.model.Role;
import com.example.revive_app.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Set<Role> getAllRoles() {
        List<Role> roles =roleRepository.findAll();
        return new HashSet<Role>(roles);
    }
}
