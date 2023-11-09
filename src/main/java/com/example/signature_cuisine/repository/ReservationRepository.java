package com.example.signature_cuisine.repository;

import com.example.signature_cuisine.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity save(ReservationEntity reservationEntity);
    List<ReservationEntity> findAll();
}
