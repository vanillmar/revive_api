package com.example.revive_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Permission {
    @Id 
    @GeneratedValue
    private Long id;
    private String name; // e.g. READ_USER
    private String description; // e.g. "Permission to read user data"
}