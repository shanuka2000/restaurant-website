package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.Reservation;
import com.example.signature_cuisine.services.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @GetMapping()
    public ResponseEntity<?> getAllReservations() {
        try {
            List<ReservationEntity> reservations = reservationService.getAll();

            if (reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empty list.");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(reservations);
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
