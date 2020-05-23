package com.boris.ecommercedashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boris.ecommercedashboard.repository.ApplicationUserRepository;
import com.boris.ecommercedashboard.user.ApplicationUser;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<String> signUp(@RequestBody ApplicationUser user) {
		
		if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
		}
				
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);
		return new ResponseEntity<>("account created", HttpStatus.CREATED);
	}
}
