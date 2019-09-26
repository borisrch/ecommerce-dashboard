package com.boris.ecommercedashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.boris.ecommercedashboard.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}