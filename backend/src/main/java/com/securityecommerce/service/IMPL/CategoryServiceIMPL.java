package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.Category;
import com.securityecommerce.repository.CategoryRepository;
import com.securityecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category create(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public List<Category> getall() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getbyId(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public Category update(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        {
            categoryRepository.deleteById(id);
        }
    }
}
