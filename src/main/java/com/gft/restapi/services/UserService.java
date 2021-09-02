package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.restapi.entities.User;
import com.gft.restapi.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findUserByEmail (String email) {
		Optional<User> foundedUser = userRepository.findUserByEmail(email);
		
		if(foundedUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found.");
		}
		
		return foundedUser.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findUserByEmail(username);
	}

	public User findUserById(Long userId) {
		Optional<User> foundedUser =  userRepository.findById(userId);
		
		if(foundedUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		
		return foundedUser.get();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

}
