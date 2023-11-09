package com.example.signature_cuisine.services.impl;

import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.model.Reservation;
import com.example.signature_cuisine.repository.ReservationRepository;
import com.example.signature_cuisine.services.PasswordHash;
import com.example.signature_cuisine.services.ReservationService;
import com.example.signature_cuisine.services.impl.formating.DateProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    private DateProcess dateProcess;
    @Autowired
    public ReservationServiceImpl(DateProcess dateProcess) {
        this.dateProcess = dateProcess;
    }

    @Override
    public boolean save(Reservation reservation) throws Exception {
        try {
            ReservationEntity reservationEntity = new ReservationEntity();

            Date processedDate = dateProcess.processDate(reservation.getReservationDate());

            reservationEntity.setFullName(reservation.getFullName());
            reservationEntity.setEmail(reservation.getEmail());
            reservationEntity.setContactNumber(reservation.getContactNumber());
            reservationEntity.setNumberOfGuest(reservation.getNumberOfGuest());
            reservationEntity.setReservationDate(processedDate);
            reservationEntity.setReservationType(reservation.getReservationType());
            reservationEntity.setSpecialRequests(reservation.getSpecialRequests());

            return reservationRepository.save(reservationEntity) != null;
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }

    @Override
    public List<ReservationEntity> getAll() throws Exception {
        try {
            return reservationRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
