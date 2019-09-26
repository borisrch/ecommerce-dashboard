package com.boris.ecommercedashboard.security;

public class SecurityConstants {

	public static final String SECRET = "TEST";
	// 6 hours
	public static final long EXPIRATION_TIME = 21600000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/sign-up";
	
}
