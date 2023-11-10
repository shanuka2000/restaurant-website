package com.example.signature_cuisine.controllers;

import com.example.signature_cuisine.entity.CustomerQueryEntity;
import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.CustomerQuery;
import com.example.signature_cuisine.services.impl.query.CustomerQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query")
public class CustomerQueryController {

    @Autowired
    private CustomerQueryServiceImpl customerQueryService;

    @GetMapping()
    public ResponseEntity<?> getAllQueries() {
        try {
            List<CustomerQueryEntity> customerQueries = customerQueryService.getAll();

            if (customerQueries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Empty List");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(customerQueries);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveQuery(@RequestBody CustomerQuery customerQuery) {
        try {
            boolean isSubmitted = customerQueryService.save(customerQuery);

            if (isSubmitted) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Query Submitted");
            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Failed to submit query. Please try again later.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
        }
    }
}
