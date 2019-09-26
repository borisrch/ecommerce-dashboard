package com.boris.ecommercedashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.boris.ecommercedashboard.model.Product;
import com.boris.ecommercedashboard.repository.ProductRepository;

@SpringBootApplication
public class EcommerceDashboardApplication {
	
	private static final Logger log = LoggerFactory.getLogger(EcommerceDashboardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcommerceDashboardApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			log.info("Reading Products from findAll()");
			
			for (Product product : repository.findAll()) {
				log.info(Long.toString(product.getId()));
				log.info(product.getName());
			}
		};
	}

}
