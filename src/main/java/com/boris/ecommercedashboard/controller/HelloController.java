package com.boris.ecommercedashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boris.ecommercedashboard.domain.AuthenticationBean;
import com.boris.ecommercedashboard.model.Product;
import com.boris.ecommercedashboard.repository.ProductRepository;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class HelloController {
	
	@Autowired
	private ProductRepository productRepository;
	
	public HelloController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping(path = "/basicauth")
	public AuthenticationBean authenticate() {
		return new AuthenticationBean("You are authenticated!");
	}
	
	@GetMapping(path = "/products")
	public List<Product> getProducts() {
		List<Product> list = new ArrayList<>();
		for (Product product : productRepository.findAll()) {
			list.add(product);
		}
		return list;
	}
	
}
