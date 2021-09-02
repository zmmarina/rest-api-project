package com.gft.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.restapi.entities.Instructor;
import com.gft.restapi.entities.Profile;
import com.gft.restapi.entities.Starter;
import com.gft.restapi.entities.User;

@Service
public class UserFactoryService {
	
	@Autowired
	private UserService userService;
	
	public User createUser(Starter starter) {
		
		User user = new User();
		user.setEmail(starter.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user.setProfile(new Profile(2L));
		
		return userService.saveUser(user);
	}
	
	public User createUser(Instructor instructor) {
		
		User user = new User();
		user.setEmail(instructor.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
		user.setProfile(new Profile(1L));
		
		return userService.saveUser(user);
		
	}





}
