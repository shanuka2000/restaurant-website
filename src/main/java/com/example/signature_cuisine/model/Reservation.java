package com.example.signature_cuisine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private String fullName;
    private String contactNumber;
    private String email;
    private String numberOfGuest;
    private String reservationDate;
    private String reservationType;
    private String specialRequests;
}
