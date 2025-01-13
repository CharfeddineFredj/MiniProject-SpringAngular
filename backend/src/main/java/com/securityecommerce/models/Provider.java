package com.securityecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class Provider extends User {

    private String matricule;
    private String service;
    private String company;

    @OneToMany(mappedBy="provider")
    private Collection<Product> products;
    @JsonIgnore
    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Provider() {
    }

    public Provider(String username, String email, String password, String matricule, String service, String company) {
        super(username, email, password);
        this.matricule = matricule;
        this.service = service;
        this.company = company;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
