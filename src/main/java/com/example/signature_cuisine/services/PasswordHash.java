package com.example.signature_cuisine.services;

public interface PasswordHash {
    String hashPassword(String password);
    boolean isPasswordValid(String password, String hashedPassword);
}
