package com.boris.ecommercedashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boris.ecommercedashboard.domain.AuthenticationBean;
import com.boris.ecommercedashboard.model.Product;
import com.boris.ecommercedashboard.repository.ProductRepository;
import com.boris.ecommercedashboard.service.ProductService;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class HelloController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	public HelloController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping(path = "/basicauth")
	public AuthenticationBean authenticate() {
		return new AuthenticationBean("You are authenticated!");
	}
	
	// TODO: Refactor Products API into their own class.
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/products")
	public List<Product> getProducts() {
		return productService.getProducts();
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping(path = "/admintest")
//	public ResponseEntity<String> adminTest() {
//		return ResponseEntity.ok("You are an admin");
//	}
}
