package com.example.signature_cuisine.repository;

import com.example.signature_cuisine.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    ReservationEntity save(ReservationEntity reservationEntity);
    List<ReservationEntity> findAll();
    List<ReservationEntity> findAllByReservationDate(Date date);
    @Query("SELECT SUM(r.numberOfGuest) FROM ReservationEntity r WHERE r.reservationDate = :reservationDate AND r.reservationType = :reservationType")
    Integer getTotalGuestsByDateAndType(@Param("reservationDate") Date reservationDate, @Param("reservationType") String reservationType);
    int countByReservationDate(Date date);
}
