package com.example.signature_cuisine.services.impl.reservation;

import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CheckAvailabilityByDate {

    private final int MAX_BOOKINGS = 10;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean isAvailable(Date reservationDate) {

        List<ReservationEntity> reservations = reservationRepository.findAllByReservationDate(reservationDate);

        return reservations.size() < MAX_BOOKINGS;
    }
}
