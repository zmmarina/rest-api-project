package com.gft.restapi.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gft.restapi.controllers.DTO.TokenDTO;
import com.gft.restapi.controllers.form.AuthenticationForm;
import com.gft.restapi.entities.User;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Value("${restapi.jwt.expiration}")
	private String expiration;
	
	@Value("${restapi.jwt.secret}")
	private String secret;
	
	@Value("${restapi.jwt.issuer}")
	private String issuer;
	
	public TokenDTO authenticate(AuthenticationForm authForm) throws AuthenticationException{
		
		Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getPassword()));
		String token = generateToken(authenticate); 
		
		return new TokenDTO(token);
	}
	
	private Algorithm createAlgorithm() {
		return Algorithm.HMAC256(secret);
	}

	private String generateToken(Authentication authenticate) {
		User principal = (User)authenticate.getPrincipal(); 
		
		Date today = new Date();
		Date expireDate = new Date(today.getTime() + Long.parseLong(expiration));
		
		return JWT.create()
				.withIssuer(issuer)
				.withExpiresAt(expireDate)
				.withSubject(principal.getId().toString())
				.sign(this.createAlgorithm());
	}
	
	public boolean checkToken(String token) {
		
		try {
		if(token == null) {
			return false;
		}
		
		JWT.require(this.createAlgorithm()).withIssuer(issuer).build().verify(token);
		
		return true;
		}catch(JWTVerificationException exception) {
			return false;
		}
	}
	
	public Long getIdUser(String token) {
		
		String subject = JWT.require(this.createAlgorithm()).withIssuer(issuer).build().verify(token).getSubject();
		return Long.parseLong(subject);		
	}

}
