package com.example.signature_cuisine.services.impl.registration;

import com.example.signature_cuisine.entity.CustomerEntity;
import com.example.signature_cuisine.repository.CustomerRepository;
import com.example.signature_cuisine.services.ValidateInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateEmail implements ValidateInformation {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String input) {
        CustomerEntity customer = customerRepository.findByEmail(input);

        return customer == null;
    }
}
