package com.example.signature_cuisine.services.impl.authentication;

import com.example.signature_cuisine.entity.CustomerEntity;
import com.example.signature_cuisine.entity.StaffEntity;
import com.example.signature_cuisine.repository.StaffRepository;
import com.example.signature_cuisine.services.AuthenticateService;
import com.example.signature_cuisine.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationStaffImpl implements AuthenticateService {

    @Autowired
    private StaffRepository staffRepository;

    private PasswordHash passwordHash;

    @Autowired
    public AuthenticationStaffImpl(PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean authenticate(String email, String password) throws Exception {

        try {
            StaffEntity customerEntity = staffRepository.findByEmail(email);
            return passwordHash.isPasswordValid(password, customerEntity.getPassword());
        } catch (Exception e) {
            throw new Exception("Something went wrong!");
        }
    }
}
