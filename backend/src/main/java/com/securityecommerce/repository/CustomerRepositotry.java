package com.securityecommerce.repository;


import com.securityecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositotry extends JpaRepository<Customer,Long> {
}
