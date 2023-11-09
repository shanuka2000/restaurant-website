package com.example.signature_cuisine.services.impl.authentication;

import com.example.signature_cuisine.entity.CustomerEntity;
import com.example.signature_cuisine.repository.CustomerRepository;
import com.example.signature_cuisine.services.AuthenticateService;
import com.example.signature_cuisine.services.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceCustomerImpl implements AuthenticateService {

    @Autowired
    private CustomerRepository customerRepository;

    private PasswordHash passwordHash;

    @Autowired
    public AuthenticateServiceCustomerImpl(PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean authenticate(String email, String password) throws Exception {

        try {
            CustomerEntity customerEntity = customerRepository.findByEmail(email);
            return passwordHash.isPasswordValid(password, customerEntity.getPassword());
        } catch (Exception e) {
            throw new Exception("Something went wrong!");
        }

    }
}
