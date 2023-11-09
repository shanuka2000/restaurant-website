package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.services.impl.authentication.AuthenticateServiceAdminImpl;
import com.example.signature_cuisine.services.impl.authentication.AuthenticateServiceCustomerImpl;
import com.example.signature_cuisine.services.impl.authentication.AuthenticationStaffImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticateServiceCustomerImpl authenticateServiceCustomer;

    @Autowired
    private AuthenticationStaffImpl authenticateStaff;

    @Autowired
    private AuthenticateServiceAdminImpl authenticateServiceAdmin;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateCustomer(@RequestParam("email") String email, @RequestParam("password") String password) {

        try {
            boolean isAuthenticated = authenticateServiceCustomer.authenticate(email, password);

            return getStringResponseEntity(isAuthenticated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @RequestMapping(value = "/staff", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateStaff(@RequestParam("email") String email, @RequestParam("password") String password) {

        try {
            boolean isAuthenticated = authenticateStaff.authenticate(email, password);

            return getStringResponseEntity(isAuthenticated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateAdmin(@RequestParam("email") String email, @RequestParam("password") String password) {

        try {
            boolean isAuthenticated = authenticateServiceAdmin.authenticate(email, password);

            return getStringResponseEntity(isAuthenticated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    private static ResponseEntity<String> getStringResponseEntity(boolean authenticated) {
        if (authenticated) {
            return ResponseEntity.status(HttpStatus.OK).body("Login Successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
}
