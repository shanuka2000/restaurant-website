package com.example.signature_cuisine.controller;

import com.example.signature_cuisine.controllers.CustomerQueryController;
import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.model.CustomerQuery;
import com.example.signature_cuisine.services.impl.query.CustomerQueryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerQueryControllerTest {
    @Mock
    private CustomerQueryServiceImpl customerQueryService;

    @InjectMocks
    private CustomerQueryController customerQueryController;

    @Test
    public void testGetAllQueries_EmptyList() throws Exception {
        // Arrange
        when(customerQueryService.getAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = customerQueryController.getAllQueries();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Empty List", response.getBody());
    }

    @Test
    public void testGetAllQueries_InternalServerError() throws Exception {
        when(customerQueryService.getAll()).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = customerQueryController.getAllQueries();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testSaveQuery_Success() throws Exception {
        CustomerQuery customerQuery = new CustomerQuery("John Doe", "john@example.com", "Question 1");
        when(customerQueryService.save(customerQuery)).thenReturn(true);

        ResponseEntity<?> response = customerQueryController.saveQuery(customerQuery);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Query Submitted", response.getBody());
    }

    @Test
    public void testSaveQuery_Failure() throws Exception {
        CustomerQuery customerQuery = new CustomerQuery("John Doe", "john@example.com", "Question 1");
        when(customerQueryService.save(customerQuery)).thenReturn(false);

        ResponseEntity<?> response = customerQueryController.saveQuery(customerQuery);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Failed to submit query. Please try again later.", response.getBody());
    }

    @Test
    public void testSaveQuery_InternalServerError() throws Exception {
        CustomerQuery customerQuery = new CustomerQuery("John Doe", "john@example.com", "Question 1");
        when(customerQueryService.save(customerQuery)).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = customerQueryController.saveQuery(customerQuery);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }
}
