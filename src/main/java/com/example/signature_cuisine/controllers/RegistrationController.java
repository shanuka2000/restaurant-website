package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.Admin;
import com.example.signature_cuisine.model.Customer;
import com.example.signature_cuisine.model.Staff;
import com.example.signature_cuisine.services.impl.registration.RegisterServiceAdminImpl;
import com.example.signature_cuisine.services.impl.registration.RegisterServiceCustomerImpl;
import com.example.signature_cuisine.services.impl.registration.RegisterServiceStaffImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RegisterServiceCustomerImpl registerServiceCustomer;

    @Autowired
    private RegisterServiceStaffImpl registerServiceStaff;

    @Autowired
    private RegisterServiceAdminImpl registerServiceAdmin;

    @PostMapping("/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        try {
            boolean isRegistered = registerServiceCustomer.register(customer.getFullName(), customer.getEmail(), customer.getPassword());

            return getStringResponseEntity(isRegistered, "Customer");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/staff")
    public ResponseEntity<?> registerStaff(@RequestBody Staff staff) {
        try {
            boolean isRegistered = registerServiceStaff.register(staff.getFullName(), staff.getEmail(), staff.getPassword());

            return getStringResponseEntity(isRegistered, "Staff");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            boolean isRegistered = registerServiceAdmin.register(admin.getFullName(), admin.getEmail(), admin.getPassword());

            return getStringResponseEntity(isRegistered, "Admin");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    private static ResponseEntity<String> getStringResponseEntity(boolean isRegistered, String userType) {
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userType + " registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userType + " registration failed");
        }
    }
}
