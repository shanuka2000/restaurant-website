package com.example.signature_cuisine.services.impl.query;

import com.example.signature_cuisine.entity.CustomerQueryEntity;
import com.example.signature_cuisine.model.CustomerQuery;
import com.example.signature_cuisine.repository.CustomerQueryRepository;
import com.example.signature_cuisine.services.CustomerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @Override
    public boolean save(CustomerQuery customerQuery) throws Exception {
        try {
            CustomerQueryEntity customerQueryEntity = new CustomerQueryEntity();

            customerQueryEntity.setFullName(customerQuery.getFullName());
            customerQueryEntity.setEmail(customerQuery.getEmail());
            customerQueryEntity.setMessage(customerQuery.getMessage());

            return customerQueryRepository.save(customerQueryEntity) != null;
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }

    @Override
    public List<CustomerQueryEntity> getAll() throws Exception {
        try {
            return customerQueryRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }

    @Override
    public int getCount() throws Exception {
        try {
            return (int) customerQueryRepository.count();
        } catch (Exception e) {
            throw new Exception("Internal server error occurred. Please contact the administrator.");
        }
    }
}
