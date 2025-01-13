package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.SubCategory;
import com.securityecommerce.repository.SubCategoryRepository;
import com.securityecommerce.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryIMPL implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Override
    public SubCategory create(SubCategory entity) {return subCategoryRepository.save(entity);}

    @Override
    public List<SubCategory> getall() {
       return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getbyId(Long id) {
        return subCategoryRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public SubCategory update(SubCategory entity) {
        return subCategoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        subCategoryRepository.deleteById(id);

    }
}
