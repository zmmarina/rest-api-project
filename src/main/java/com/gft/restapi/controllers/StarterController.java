package com.gft.restapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.restapi.entities.Starter;
import com.gft.restapi.events.CreatedResourceEvent;
import com.gft.restapi.repositories.StarterRepository;
import com.gft.restapi.services.StarterService;

@RestController
@RequestMapping("/starter")
public class StarterController {
	
	@Autowired
	private StarterRepository starterRepository;
	
	@Autowired
	private StarterService starterService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Starter> listStarters(){
		return starterRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Starter>> findStarterById(@PathVariable Long id){
		Optional<Starter> foundedStarter = starterRepository.findById(id);
		return !foundedStarter.isEmpty() ? ResponseEntity.ok(foundedStarter) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Starter> createStarter(@Valid @RequestBody Starter starter, HttpServletResponse response){
		Starter savedStarter = starterRepository.save(starter);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedStarter.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedStarter);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Starter> updateStarter(@PathVariable Long id, @Valid @RequestBody Starter starter){
		Starter savedStarter = starterService.updateStarter(id, starter);
		return ResponseEntity.ok(savedStarter);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStarter(@PathVariable Long id) {
		starterRepository.deleteById(id);
	}
	

}
