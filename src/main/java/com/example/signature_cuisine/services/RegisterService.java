package com.example.signature_cuisine.services;

public interface RegisterService {
    boolean register(String fullName, String email, String password) throws Exception;
}
