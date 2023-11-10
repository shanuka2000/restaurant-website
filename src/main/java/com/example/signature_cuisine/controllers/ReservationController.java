package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.Reservation;
import com.example.signature_cuisine.services.impl.reservation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @GetMapping()
    public ResponseEntity<?> getAllReservations() {
        try {
            List<ReservationEntity> reservations = reservationService.getAll();

            if (reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Empty List");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(reservations);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/availability")
    public ResponseEntity<?> checkAvailability(@RequestParam("reservationDate") String reservationDate, @RequestParam("reservationType") String reservationType, @RequestParam("guestCount") int guestCount) {
        try {
            boolean isAvailable = reservationService.isReservable(reservationDate, reservationType, guestCount);

            if (isAvailable) {
                return ResponseEntity.status(HttpStatus.OK).body("Reservable.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Reservation not available.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveReservation(@RequestBody Reservation reservation) {
        try {
            boolean isReserved = reservationService.save(reservation);

            if (isReserved) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Reservation Created");
            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Data validation failed. Please check your request.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }
}
