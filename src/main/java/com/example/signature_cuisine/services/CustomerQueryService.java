package com.example.signature_cuisine.services;

import com.example.signature_cuisine.entity.CustomerQueryEntity;
import com.example.signature_cuisine.model.CustomerQuery;

import java.util.List;

public interface CustomerQueryService {
    boolean save(CustomerQuery customerQuery) throws Exception;
    List<CustomerQueryEntity> getAll() throws Exception;
}
