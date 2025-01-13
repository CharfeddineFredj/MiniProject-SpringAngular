package com.securityecommerce.service.IMPL;


import com.securityecommerce.models.Order;
import com.securityecommerce.repository.OrderRepository;
import com.securityecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order create(Order entity) {
            return orderRepository.save(entity);
    }

    @Override
    public List<Order> getall() {
        return orderRepository.findAll();
    }

    @Override
    public Order getbyId(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
    }

    @Override
    public Order update(Order entity) {
        return orderRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
