package com.example.revive_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Permission {
    @Id 
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // e.g. READ_USER
    
    private String description; // e.g. "Permission to read user data"

    // Default constructor
    public Permission() {}

    //getter and setter methods
    public Long getId() {
        return id;
    }   
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}