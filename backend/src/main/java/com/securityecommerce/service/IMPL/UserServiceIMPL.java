package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.User;
import com.securityecommerce.repository.UserRepository;
import com.securityecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    public User getbyId(Long id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}
