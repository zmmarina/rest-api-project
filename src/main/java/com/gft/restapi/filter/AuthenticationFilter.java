package com.gft.restapi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gft.restapi.entities.User;
import com.gft.restapi.services.AuthenticationService;
import com.gft.restapi.services.UserService;

public class AuthenticationFilter extends OncePerRequestFilter{
	
	
	private AuthenticationService authenticationService;
	private UserService userService;
	
	public AuthenticationFilter(AuthenticationService authenticationService, UserService userService) {
		this.authenticationService = authenticationService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		String token = null;
		if(header != null && header.startsWith("Bearer ")) {
			token = header.substring(7, header.length());
		}
		
		if(authenticationService.checkToken(token)) {
			Long userId = authenticationService.getIdUser(token);
			User user = userService.findUserById(userId);
			SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
		}
		
		filterChain.doFilter(request, response);
		
	}

}
