package com.boris.ecommercedashboard.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.boris.ecommercedashboard.user.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.boris.ecommercedashboard.security.SecurityConstants.EXPIRATION_TIME;
import static com.boris.ecommercedashboard.security.SecurityConstants.SECRET;
import static com.boris.ecommercedashboard.security.SecurityConstants.HEADER_STRING;
import static com.boris.ecommercedashboard.security.SecurityConstants.SIGN_UP_URL;
import static com.boris.ecommercedashboard.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	// Parse the user's credentials and issue them to AuthenticationManager.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			ApplicationUser creds = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);
			
			log.info("Username: " + creds.getUsername());
			log.info("Password: " + creds.getPassword());
			
			/* Pass UsernamePasswordAuthenticationToken to default AuthenticationProvider which will use the userDetailsService
			 * to get the user based on user name, and then compare that user's password with the one in the authentication token. */
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Called when a user successfully logs in.
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {	
		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(HMAC512(SECRET.getBytes()));
		
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}
