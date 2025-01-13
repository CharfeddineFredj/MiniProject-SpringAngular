package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.Provider;
import com.securityecommerce.repository.ProviderRepository;
import com.securityecommerce.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProviderServiceIMPL implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider create(Provider entity) {
        return providerRepository.save(entity);
    }

    @Override
    public List<Provider> getall() {
        return providerRepository.findAll();
    }

    @Override
    public Provider getbyId(Long id) {
        return providerRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public Provider update(Provider entity) {
        return providerRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
         providerRepository.deleteById(id);
    }
}
