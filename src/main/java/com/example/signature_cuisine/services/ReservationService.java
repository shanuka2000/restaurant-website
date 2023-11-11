package com.example.signature_cuisine.services;

import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.model.Reservation;

import java.util.List;

public interface ReservationService {
    boolean save(Reservation reservation) throws Exception;
    List<ReservationEntity> getAll() throws Exception;
    boolean isReservable(String reservationDate, String reservationType, int guestCount) throws Exception;
    int getCount(String date) throws Exception;
    List<ReservationEntity> getByDate(String date) throws Exception;
}
