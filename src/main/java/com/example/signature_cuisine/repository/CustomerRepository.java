package com.example.signature_cuisine.repository;

import com.example.signature_cuisine.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
    CustomerEntity save(CustomerEntity customer);
    long count();
}
