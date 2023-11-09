package com.example.signature_cuisine.services.impl.security;

import com.example.signature_cuisine.services.PasswordHash;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Hash256 implements PasswordHash {
    @Override
    public String hashPassword(String password) {
        return hash(password);
    }

    @Override
    public boolean isPasswordValid(String password, String hashedPassword) {
        String hashedInputPassword = hash(password);
        return hashedInputPassword.equals(hashedPassword);
    }

    private String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
            for (byte hashByte : hashBytes) {
                hexString.append(String.format("%02x", hashByte));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
