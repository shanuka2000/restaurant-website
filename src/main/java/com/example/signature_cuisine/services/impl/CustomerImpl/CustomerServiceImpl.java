package com.example.signature_cuisine.services.impl.CustomerImpl;

import com.example.signature_cuisine.repository.CustomerRepository;
import com.example.signature_cuisine.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public int getCount() throws Exception {
        try {
            return (int) customerRepository.count();
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
