package com.example.signature_cuisine.services.impl.registration;

import com.example.signature_cuisine.entity.StaffEntity;
import com.example.signature_cuisine.repository.StaffRepository;
import com.example.signature_cuisine.services.RegisterService;
import com.example.signature_cuisine.services.impl.security.Hash256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceStaffImpl implements RegisterService  {
    @Autowired
    private StaffRepository staffRepository;
    private final Hash256 passwordHash;
    private final ValidateEmail validateEmail;
    public RegisterServiceStaffImpl(Hash256 passwordHash, ValidateEmail validateEmail) {
        this.passwordHash = passwordHash;
        this.validateEmail = validateEmail;
    }
    @Override
    public boolean register(String fullName, String email, String password) throws Exception {
        try {
            if (staffRepository.findByEmail(email) != null) {
                return false;
            }
            StaffEntity staffEntity = new StaffEntity();

            staffEntity.setFullName(fullName);
            staffEntity.setEmail(email);
            staffEntity.setPassword(passwordHash.hashPassword(password));

            return staffRepository.save(staffEntity) != null;
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
