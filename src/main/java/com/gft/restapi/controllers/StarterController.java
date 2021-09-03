package com.gft.restapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.restapi.controllers.DTO.StarterDTO;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<StarterDTO> listStarters(){
		
		List<StarterDTO> starterDTOList = new ArrayList<>();
		List<Starter> starterList = starterRepository.findAll();
		
		for(Starter starter : starterList) {
			StarterDTO starterDTO = StarterDTO.from(starter);
			starterDTOList.add(starterDTO);
		}
		return starterDTOList;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<StarterDTO> findStarterById(@PathVariable Long id){
		Optional<Starter> foundedStarter = starterRepository.findById(id);
		StarterDTO starterDTO = StarterDTO.from(foundedStarter);
		return !foundedStarter.isEmpty() ? ResponseEntity.ok(starterDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<StarterDTO> createStarter(@Valid @RequestBody Starter starter, HttpServletResponse response){
		Starter savedStarter = starterService.saveStarter(starter);
		StarterDTO starterDTO = StarterDTO.from(savedStarter);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedStarter.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(starterDTO);		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<StarterDTO> updateStarter(@PathVariable Long id, @Valid @RequestBody Starter starter){
		Starter savedStarter = starterService.updateStarter(id, starter);
		StarterDTO starterDTO = StarterDTO.from(savedStarter);
		return ResponseEntity.ok(starterDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStarter(@PathVariable Long id) {
		starterRepository.deleteById(id);
	}
	

}
