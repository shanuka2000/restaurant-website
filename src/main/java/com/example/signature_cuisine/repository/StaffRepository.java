package com.example.signature_cuisine.repository;

import com.example.signature_cuisine.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    StaffEntity findByEmail(String email);
}
