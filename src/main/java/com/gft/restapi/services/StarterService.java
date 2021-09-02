package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.restapi.entities.Starter;
import com.gft.restapi.repositories.StarterRepository;

@Service
public class StarterService {
	
	@Autowired
	private StarterRepository starterRepository;
	
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

}
