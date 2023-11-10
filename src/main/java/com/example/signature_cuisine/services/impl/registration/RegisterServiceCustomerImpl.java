package com.example.signature_cuisine.services.impl.registration;

import com.example.signature_cuisine.entity.CustomerEntity;
import com.example.signature_cuisine.repository.CustomerRepository;
import com.example.signature_cuisine.services.RegisterService;
import com.example.signature_cuisine.services.impl.security.Hash256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceCustomerImpl implements RegisterService {
    private Hash256 passwordHash;
    private ValidateEmail validateEmail;
    @Autowired
    private CustomerRepository customerRepository;
    public RegisterServiceCustomerImpl (Hash256 passwordHash, ValidateEmail validateEmail) {
        this.passwordHash = passwordHash;
        this.validateEmail = validateEmail;
    }
    @Override
    public boolean register(String fullName, String email, String password) throws Exception {
        try {
            CustomerEntity customerEntity = new CustomerEntity();

            String hashedPassword = passwordHash.hashPassword(password);

            customerEntity.setFullName(fullName);
            customerEntity.setEmail(email);
            customerEntity.setPassword(hashedPassword);

            if (validateEmail.isValid(email)) {
                return customerRepository.save(customerEntity) != null;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
