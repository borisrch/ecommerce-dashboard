package com.boris.ecommercedashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boris.ecommercedashboard.model.Product;
import com.boris.ecommercedashboard.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	public ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		List<Product> list = new ArrayList<>();
		for (Product product : productRepository.findAll()) {
			list.add(product);
		}
		return list;
	}

}
