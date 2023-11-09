package com.example.signature_cuisine.services.impl.reservation;

import com.example.signature_cuisine.entity.ReservationEntity;
import com.example.signature_cuisine.model.Reservation;
import com.example.signature_cuisine.repository.ReservationRepository;
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
    private CheckAvailabilityByDate checkAvailabilityByDate;
    private CheckAvailabilityByGuestCount checkAvailabilityByGuestCount;
    @Autowired
    public ReservationServiceImpl(DateProcess dateProcess, CheckAvailabilityByDate checkAvailabilityByDate, CheckAvailabilityByGuestCount checkAvailabilityByGuestCount) {
        this.dateProcess = dateProcess;
        this.checkAvailabilityByDate = checkAvailabilityByDate;
        this.checkAvailabilityByGuestCount = checkAvailabilityByGuestCount;
    }

    @Override
    public boolean save(Reservation reservation) throws Exception {
        try {
            ReservationEntity reservationEntity = new ReservationEntity();

            Date processedDate = dateProcess.processDate(reservation.getReservationDate());

            reservationEntity.setFullName(reservation.getFullName());
            reservationEntity.setEmail(reservation.getEmail());
            reservationEntity.setContactNumber(Integer.parseInt(reservation.getContactNumber()));
            reservationEntity.setNumberOfGuest(Integer.parseInt(reservation.getNumberOfGuest()));
            reservationEntity.setReservationDate(processedDate);
            reservationEntity.setReservationType(reservation.getReservationType());
            reservationEntity.setSpecialRequests(reservation.getSpecialRequests());

            if (isReservable(reservation.getReservationDate(), reservation.getReservationType(), Integer.parseInt(reservation.getNumberOfGuest()))) {
                return reservationRepository.save(reservationEntity) != null;
            } else {
                return false;
            }
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

    @Override
    public boolean isReservable(String reservationDate, String reservationType, int guestCount) throws Exception {
        try {
            Date processedDate = dateProcess.processDate(reservationDate);

            boolean isAvailableByDate = checkAvailabilityByDate.isAvailable(processedDate);
            boolean isAvailableByGuestCount = checkAvailabilityByGuestCount.isAvailable(processedDate, reservationType, guestCount);

            return isAvailableByDate && isAvailableByGuestCount;

        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
