package com.example.signature_cuisine.services;


public interface AuthenticateService {
    boolean authenticate(String email, String password) throws Exception;
}
