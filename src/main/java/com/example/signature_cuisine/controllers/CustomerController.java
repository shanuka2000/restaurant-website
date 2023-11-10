package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.services.impl.CustomerImpl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping()
    public ResponseEntity<?> getUserCount() {
        try {
            int count = customerService.getCount();

            return ResponseEntity.status(HttpStatus.OK).body(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }
}
