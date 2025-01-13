package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.Product;
import com.securityecommerce.repository.ProductRepository;
import com.securityecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduceServiceIMPL implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> getall() {
        return productRepository.findAll();
    }

    @Override
    public Product getbyId(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public Product update(Product entity) {return productRepository.save(entity);}

    @Override
    public void  delete(Long id) {
        productRepository.deleteById(id);
    }
}
