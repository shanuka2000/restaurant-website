package com.example.signature_cuisine.controller;

import com.example.signature_cuisine.controllers.ReservationController;
import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.Reservation;
import com.example.signature_cuisine.services.impl.reservation.ReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {
    @Mock
    private ReservationServiceImpl reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    public void testGetAllReservations_Success() throws Exception {
        List<ReservationEntity> reservations = Arrays.asList(
                new ReservationEntity(1L, "John Doe", 123456789, "john@example.com", 4, new Date(), "Dinner", "Special requests 1"),
                new ReservationEntity(2L, "Jane Doe", 987654321, "jane@example.com", 2, new Date(), "Lunch", "Special requests 2")
        );
        when(reservationService.getAll()).thenReturn(reservations);

        ResponseEntity<?> response = reservationController.getAllReservations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    public void testGetAllReservations_EmptyList() throws Exception {
        when(reservationService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = reservationController.getAllReservations();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Empty list.", response.getBody());
    }

    @Test
    public void testGetAllReservations_InternalServerError() throws Exception {
        when(reservationService.getAll()).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = reservationController.getAllReservations();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testCheckAvailability_Reservable() throws Exception {
        when(reservationService.isReservable(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(true);

        ResponseEntity<?> response = reservationController.checkAvailability("2023-01-01", "Dinner", 4);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reservable.", response.getBody());
    }

    @Test
    public void testCheckAvailability_NotReservable() throws Exception {
        when(reservationService.isReservable(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(false);

        ResponseEntity<?> response = reservationController.checkAvailability("2023-01-01", "Dinner", 4);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Reservation not available.", response.getBody());
    }

    @Test
    public void testCheckAvailability_InternalServerError() throws Exception {
        when(reservationService.isReservable(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = reservationController.checkAvailability("2023-01-01", "Dinner", 4);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testSaveReservation_Success() throws Exception {
        Reservation reservation = new Reservation("John Doe", "123456789", "john@example.com", "4", "2023-01-01", "Dinner", "Special requests");
        when(reservationService.save(reservation)).thenReturn(true);

        ResponseEntity<?> response = reservationController.saveReservation(reservation);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Reservation Created", response.getBody());
    }

    @Test
    public void testSaveReservation_Failure() throws Exception {
        Reservation reservation = new Reservation("John Doe", "123456789", "john@example.com", "4", "2023-01-01", "Dinner", "Special requests");
        when(reservationService.save(reservation)).thenReturn(false);

        ResponseEntity<?> response = reservationController.saveReservation(reservation);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Data validation failed. Please check your request.", response.getBody());
    }

    @Test
    public void testSaveReservation_InternalServerError() throws Exception {
        Reservation reservation = new Reservation("John Doe", "123456789", "john@example.com", "4", "2023-01-01", "Dinner", "Special requests");
        when(reservationService.save(reservation)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = reservationController.saveReservation(reservation);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }
}
