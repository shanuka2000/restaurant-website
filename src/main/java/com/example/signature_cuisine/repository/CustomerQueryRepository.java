package com.example.signature_cuisine.repository;

import com.example.signature_cuisine.entity.CustomerQueryEntity;
import com.example.signature_cuisine.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerQueryRepository extends JpaRepository<CustomerQueryEntity, Long> {
    CustomerQueryEntity save(CustomerQueryEntity customerQueryEntity);
    List<CustomerQueryEntity> findAll();
    long count();
}
