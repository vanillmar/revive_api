/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Persons")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Person extends BaseAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- BASIC INFO ---
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(unique = true)
    private String nationalId; // e.g. BI, passport, or national document

    @Column(columnDefinition = "TEXT")
    private String bio;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ContactInfo> contactInfos = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    // --- UTILITY METHODS ---
    public void addAddress(Address address) {
        address.setPerson(this);
        if (address.isPrimary()) {
            // ensure only one primary address
            this.addresses.forEach(a -> a.setPrimary(false));
        }
        this.addresses.add(address);
    }

    public void setPrimaryAddress(Long addressId) {
        this.addresses.forEach(a -> a.setPrimary(a.getId().equals(addressId)));
    }

    public Address getPrimaryAddress() {
        return this.addresses.stream().filter(Address::isPrimary).findFirst().orElse(null);
    }

}
