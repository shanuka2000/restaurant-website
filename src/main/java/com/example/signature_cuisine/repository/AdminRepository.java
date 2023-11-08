package com.example.signature_cuisine.repository;

import com.example.signature_cuisine.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
