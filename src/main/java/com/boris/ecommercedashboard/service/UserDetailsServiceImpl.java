package com.boris.ecommercedashboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boris.ecommercedashboard.repository.ApplicationUserRepository;
import com.boris.ecommercedashboard.security.JWTAuthenticationFilter;
import com.boris.ecommercedashboard.user.ApplicationUser;
import com.boris.ecommercedashboard.user.Role;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserRepository applicationUserRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), getAuthority(applicationUser));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(ApplicationUser user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			log.info(role.getName());
			
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
			log.info(simpleGrantedAuthority.toString());
			
			authorities.add(simpleGrantedAuthority);
		});
		
		log.info("HashSet: " + authorities.toString());
		
		return authorities;
	}

}
