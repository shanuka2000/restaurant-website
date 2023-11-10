package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.Customer;
import com.example.signature_cuisine.services.impl.registration.RegisterServiceCustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RegisterServiceCustomerImpl registerServiceCustomer;

    @PostMapping("/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        try {
            boolean isRegistered = registerServiceCustomer.register(customer.getFullName(), customer.getEmail(), customer.getPassword());

            if (isRegistered) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Registration Failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }
}
