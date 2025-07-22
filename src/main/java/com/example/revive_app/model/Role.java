package com.example.revive_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Role {
    @Id 
    @GeneratedValue
    private Long id;
    private String name; // e.g. ROLE_ADMIN
    private String description; // e.g. "Administrator role with full access"

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();
} 
