package com.example.signature_cuisine.controller;

import com.example.signature_cuisine.controllers.AuthenticateController;
import com.example.signature_cuisine.exception.ErrorResponse;
import com.example.signature_cuisine.services.impl.authentication.AuthenticateServiceAdminImpl;
import com.example.signature_cuisine.services.impl.authentication.AuthenticateServiceCustomerImpl;
import com.example.signature_cuisine.services.impl.authentication.AuthenticationServiceStaffImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthenticateControllerTest {

    @Mock
    private AuthenticateServiceCustomerImpl authenticateServiceCustomer;

    @Mock
    private AuthenticationServiceStaffImpl authenticateStaff;

    @Mock
    private AuthenticateServiceAdminImpl authenticateServiceAdmin;

    @InjectMocks
    private AuthenticateController authenticateController;

    @Test
    public void testAuthenticateCustomer_Success() throws Exception {
        Mockito.when(authenticateServiceCustomer.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        ResponseEntity<?> response = authenticateController.authenticateCustomer("test@example.com", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login Successful.", response.getBody());
    }

    @Test
    public void testAuthenticateCustomer_Failure() throws Exception {
        Mockito.when(authenticateServiceCustomer.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

        ResponseEntity<?> response = authenticateController.authenticateCustomer("test@example.com", "password123");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password.", response.getBody());
    }

    @Test
    public void testExceptionHandling_InternalServerError() throws Exception {
        Mockito.when(authenticateServiceCustomer.authenticate(Mockito.anyString(), Mockito.anyString())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = authenticateController.authenticateCustomer("test@example.com", "password123");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testAuthenticateStaff_Success() throws Exception {
        Mockito.when(authenticateStaff.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        ResponseEntity<?> response = authenticateController.authenticateStaff("test@example.com", "password123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login Successful.", response.getBody());
    }

    @Test
    public void testAuthenticateStaff_Failure() throws Exception {
        Mockito.when(authenticateStaff.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

        ResponseEntity<?> response = authenticateController.authenticateStaff("test@example.com", "password123");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password.", response.getBody());
    }

    @Test
    public void testAuthenticateAdmin_Success() throws Exception {
        Mockito.when(authenticateServiceAdmin.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        ResponseEntity<?> response = authenticateController.authenticateAdmin("admin@example.com", "adminPassword");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login Successful.", response.getBody());
    }

    @Test
    public void testAuthenticateAdmin_Failure() throws Exception {
        Mockito.when(authenticateServiceAdmin.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

        ResponseEntity<?> response = authenticateController.authenticateAdmin("admin@example.com", "adminPassword");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password.", response.getBody());
    }

    @Test
    public void testExceptionHandling_InternalServerError_Customer() throws Exception {
        Mockito.when(authenticateServiceCustomer.authenticate(Mockito.anyString(), Mockito.anyString())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = authenticateController.authenticateCustomer("test@example.com", "password123");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testAuthenticateCustomer_NullEmailAndPassword() {
        ResponseEntity<?> response = authenticateController.authenticateCustomer(null, null);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password.", response.getBody());
    }

    @Test
    public void testAuthenticateAdmin_NullEmailAndPassword() {
        ResponseEntity<?> response = authenticateController.authenticateAdmin(null, null);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password.", response.getBody());
    }

    @Test
    public void testAuthenticateCustomer_InternalServerError() throws Exception {
        Mockito.when(authenticateServiceCustomer.authenticate(Mockito.anyString(), Mockito.anyString())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = authenticateController.authenticateCustomer("test@example.com", "password123");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testAuthenticateStaff_InternalServerError() throws Exception {
        Mockito.when(authenticateStaff.authenticate(Mockito.anyString(), Mockito.anyString())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = authenticateController.authenticateStaff("staff@example.com", "staffPassword");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }

    @Test
    public void testAuthenticateAdmin_InternalServerError() throws Exception {
        Mockito.when(authenticateServiceAdmin.authenticate(Mockito.anyString(), Mockito.anyString())).thenThrow(new RuntimeException("Internal Server Error"));

        ResponseEntity<?> response = authenticateController.authenticateAdmin("admin@example.com", "adminPassword");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ErrorResponse.class, response.getBody().getClass());
    }
}
