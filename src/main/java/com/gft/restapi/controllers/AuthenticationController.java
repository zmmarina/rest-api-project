package com.gft.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.restapi.controllers.DTO.TokenDTO;
import com.gft.restapi.controllers.form.AuthenticationForm;
import com.gft.restapi.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autentication(@RequestBody AuthenticationForm authForm){
		
		try {
			
			return ResponseEntity.ok(authenticationService.authenticate(authForm));
			
		}catch(AuthenticationException ae) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}	
		
	}

}
