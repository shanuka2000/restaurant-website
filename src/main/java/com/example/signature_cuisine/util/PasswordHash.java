package com.example.signature_cuisine.util;

public interface PasswordHash {
    String hashPassword(String password);
    boolean isPasswordValid(String password, String hashedPassword);
}
