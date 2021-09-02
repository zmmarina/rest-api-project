package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.restapi.entities.Starter;
import com.gft.restapi.entities.User;
import com.gft.restapi.repositories.StarterRepository;

@Service
public class StarterService {
	
	@Autowired
	private StarterRepository starterRepository;
	
	@Autowired
	private UserFactoryService userFactoryService;
	
	public Starter updateStarter(Long id, Starter starter) {
		Starter foundedStarter = findStarterById(id);
		BeanUtils.copyProperties(starter, foundedStarter, "id");
		return starterRepository.save(foundedStarter);
	}
	
	public Starter findStarterById(Long id) {
		Optional<Starter> foundedStarter = starterRepository.findById(id);
		
		if(foundedStarter.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return foundedStarter.get();
	}
	
	@Transactional
	public Starter saveStarter(Starter starter) {
		Starter starter2 = starterRepository.save(starter);
		User user = userFactoryService.createUser(starter2);
		starter2.setUser(user);
		
		starterRepository.save(starter2);
		return starter2;
	}

}
