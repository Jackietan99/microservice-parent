package com.jsfd.microservice.auth.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.security.Principal;

public abstract class SpringSecurityUtils {

	private static final Logger log = LoggerFactory.getLogger(SpringSecurityUtils.class);
	
	//@see com.hs.auth.Constants.PASSWORD_ENCODER_STRENGTH
	private static final int PASSWORD_ENCODER_STRENGTH = 4;
	
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(PASSWORD_ENCODER_STRENGTH);
	}
	public static String encode(CharSequence rawPassword) {
		return passwordEncoder().encode(rawPassword);
	}
	public static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		return context.getAuthentication();
	}
	
	public static boolean hasAuthentication() {
		return getAuthentication() != null;
	}
	
	public static UserDetailsImpl getCurrentUser() {
		UserDetailsImpl user = null;
		Authentication auth = getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			user = (UserDetailsImpl) auth.getPrincipal();
		}
		return user;
	}
	
	public static UserDetailsImpl getCurrentUser(Principal principal) {
		//UsernamePasswordAuthenticationToken
		UserDetailsImpl user = null;
		if (principal instanceof Authentication) {
			Authentication auth = (Authentication)principal;
			user = (UserDetailsImpl) auth.getPrincipal();
		}
		return user;
	}
	
	public static String getCurrentUsername() {
		log.debug("getCurrentUsername");
		/*
		String username = null;
		UserDetailsImpl user = getCurrentUser();
		if (user != null) {
			username = user.getUsername();
		}
		return username; 
		 */
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		if (principal instanceof Principal) {
			return ((Principal) principal).getName();
		}
		return String.valueOf(principal);
	}
	
	public static String getCurrentUserId() {
		String userId = null;
		UserDetailsImpl user = getCurrentUser();
		if (user != null) {
			userId = user.getId();
		}
		return userId;
	}
	
	public static String getCurrentUserIp() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return "";
		}
		Object details = authentication.getDetails();
		if (!(details instanceof WebAuthenticationDetails)) {
			return "";
		}
		
		WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;

		return webDetails.getRemoteAddress();
	}

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		passwordEncoder.encode("password");
		System.out.println(passwordEncoder.encode("password"));
		// $2a$04$bZoduxUE/I.nngksdHBOR.2YfFm5JvbFXPXPmOId3tZ06QXZV4rPa
		// $2a$04$ze51G/2Cz/SYJBkqCXlkxuqnOV63sLZFdZPTrZVGkgD/4tj.9nN7q
		// $2a$04$u4iDwETYDV8LntA4pI7nieIn8eE/9b8iE.Ub2Qs9FvHll5Yt4j1fu

		boolean isMatches = passwordEncoder.matches("password", "$2a$04$ze51G/2Cz/SYJBkqCXlkxuqnOV63sLZFdZPTrZVGkgD/4tj.9nN7q");
		System.out.println(isMatches);
	}
}
