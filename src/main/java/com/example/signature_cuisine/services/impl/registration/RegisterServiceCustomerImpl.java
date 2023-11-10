package com.example.signature_cuisine.services.impl.registration;

import com.example.signature_cuisine.entity.CustomerEntity;
import com.example.signature_cuisine.repository.CustomerRepository;
import com.example.signature_cuisine.services.RegisterService;
import com.example.signature_cuisine.services.impl.security.Hash256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceCustomerImpl implements RegisterService {
    private final Hash256 passwordHash;

    @Autowired
    private CustomerRepository customerRepository;

    public RegisterServiceCustomerImpl (Hash256 passwordHash) {
        this.passwordHash = passwordHash;
    }
    @Override
    public boolean register(String fullName, String email, String password) throws Exception {
        try {
            if (customerRepository.findByEmail(email) != null) {
                return false;
            }
            CustomerEntity customerEntity = new CustomerEntity();

            customerEntity.setFullName(fullName);
            customerEntity.setEmail(email);
            customerEntity.setPassword(passwordHash.hashPassword(password));

            return customerRepository.save(customerEntity) != null;
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
