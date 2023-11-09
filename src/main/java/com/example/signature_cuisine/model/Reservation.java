package com.example.signature_cuisine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private String fullName;
    private int contactNumber;
    private String email;
    private int numberOfGuest;
    private String reservationDate;
    private String reservationType;
    private String specialRequests;
}
