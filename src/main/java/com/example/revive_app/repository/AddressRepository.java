package com.example.revive_app.repository;

import com.example.revive_app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // You can add custom query methods here if needed
}
