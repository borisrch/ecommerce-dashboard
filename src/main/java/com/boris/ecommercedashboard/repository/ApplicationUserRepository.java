package com.boris.ecommercedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boris.ecommercedashboard.user.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}
