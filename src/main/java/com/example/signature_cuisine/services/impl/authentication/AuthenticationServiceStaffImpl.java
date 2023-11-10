package com.example.signature_cuisine.services.impl.authentication;

import com.example.signature_cuisine.entity.StaffEntity;
import com.example.signature_cuisine.repository.StaffRepository;
import com.example.signature_cuisine.services.AuthenticateService;
import com.example.signature_cuisine.services.impl.security.Hash256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceStaffImpl implements AuthenticateService {

    @Autowired
    private StaffRepository staffRepository;
    private final Hash256 passwordHash;
    @Autowired
    public AuthenticationServiceStaffImpl(Hash256 passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean authenticate(String email, String password) throws Exception {

        try {
            StaffEntity staffEntity = staffRepository.findByEmail(email);
            return passwordHash.isPasswordValid(password, staffEntity.getPassword());
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
