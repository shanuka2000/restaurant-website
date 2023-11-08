package com.example.signature_cuisine.util;

public interface PasswordHasher {
    String hashPassword(String password);
    boolean isPasswordValid(String password, String hashedPassword);
}
