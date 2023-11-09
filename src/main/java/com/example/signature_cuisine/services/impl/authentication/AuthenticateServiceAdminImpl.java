package com.example.signature_cuisine.services.impl.authentication;

import com.example.signature_cuisine.entity.AdminEntity;
import com.example.signature_cuisine.repository.AdminRepository;
import com.example.signature_cuisine.services.AuthenticateService;
import com.example.signature_cuisine.services.impl.security.Hash256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceAdminImpl implements AuthenticateService {

    @Autowired
    private AdminRepository adminRepository;

    private Hash256 passwordHash;

    public AuthenticateServiceAdminImpl(Hash256 passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean authenticate(String email, String password) throws Exception {
        try {
            AdminEntity adminEntity = adminRepository.findByEmail(email);
            return passwordHash.isPasswordValid(password, adminEntity.getPassword());
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
