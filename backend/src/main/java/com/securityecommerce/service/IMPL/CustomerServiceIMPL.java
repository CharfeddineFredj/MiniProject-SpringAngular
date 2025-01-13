package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.Customer;
import com.securityecommerce.repository.CustomerRepositotry;
import com.securityecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerRepositotry customerRepositotry;

    @Override
    public Customer create(Customer entity) {
        return customerRepositotry.save(entity);
    }

    @Override
    public List<Customer> getall() {
        return customerRepositotry.findAll();
    }

    @Override
    public Customer getbyId(Long id) {
        return customerRepositotry.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public Customer update(Customer entity) {
        return customerRepositotry.save(entity);
    }

    @Override
    public void delete(Long id) {
       customerRepositotry.deleteById(id);
    }
}
