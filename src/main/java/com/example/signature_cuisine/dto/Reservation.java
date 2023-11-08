package com.example.signature_cuisine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private String fullName;
    private int contactNumber;
    private String email;
    private int numberOfGuest;
    private Date reservationDate;
    private String reservationType;
    private String specialRequests;
}
