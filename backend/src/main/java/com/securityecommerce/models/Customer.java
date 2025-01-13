package com.securityecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class Customer extends User{


    private String adress;
    private String city;

    @OneToMany(mappedBy="customer")
    private Collection<Order> orders;
    @JsonIgnore
    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Customer() {
    }
    public Customer(String username, String email, String password, String adress, String city) {
        super(username, email, password);
        this.adress = adress;
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
