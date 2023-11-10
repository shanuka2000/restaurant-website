package com.example.signature_cuisine.services.impl.registration;

import com.example.signature_cuisine.entity.AdminEntity;
import com.example.signature_cuisine.repository.AdminRepository;
import com.example.signature_cuisine.services.RegisterService;
import com.example.signature_cuisine.services.impl.security.Hash256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceAdminImpl implements RegisterService {
    private final Hash256 passwordHash;

    @Autowired
    private AdminRepository adminRepository;

    public RegisterServiceAdminImpl(Hash256 passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean register(String fullName, String email, String password) throws Exception {
        try {
            if (adminRepository.findByEmail(email) != null) {
                return false;
            }

            AdminEntity adminEntity = new AdminEntity();

            adminEntity.setFullName(fullName);
            adminEntity.setEmail(email);
            adminEntity.setPassword(passwordHash.hashPassword(password));

            return adminRepository.save(adminEntity) != null;
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
