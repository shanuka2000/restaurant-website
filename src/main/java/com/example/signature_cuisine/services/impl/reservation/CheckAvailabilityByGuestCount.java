package com.example.signature_cuisine.services.impl.reservation;

import com.example.signature_cuisine.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CheckAvailabilityByGuestCount {
    private final int MAX_GUESTS = 50;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean isAvailable(Date reservationDate, String reservationType, int reservationGuestCount) {
        Integer guestCount = reservationRepository.getTotalGuestsByDateAndType(reservationDate, reservationType);

        return (guestCount == null && reservationGuestCount <= MAX_GUESTS) ||
                (guestCount != null && guestCount + reservationGuestCount <= MAX_GUESTS);
    }
}
