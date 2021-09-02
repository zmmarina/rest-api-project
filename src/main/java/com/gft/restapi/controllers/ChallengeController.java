package com.gft.restapi.controllers;

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

import com.gft.restapi.entities.Challenge;
import com.gft.restapi.events.CreatedResourceEvent;
import com.gft.restapi.repositories.ChallengeRepository;
import com.gft.restapi.services.ChallengeService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private ChallengeService challengeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Challenge> listChallenges(){
		return challengeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Optional<Challenge>> findChallengById(@PathVariable Long id){
		Optional<Challenge> foundedChallenge = challengeRepository.findById(id);
		return !foundedChallenge.isEmpty() ? ResponseEntity.ok(foundedChallenge) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Challenge> createChallenge (@Valid @RequestBody Challenge challenge, HttpServletResponse response){
		Challenge savedChallenge = challengeRepository.save(challenge);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedChallenge.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedChallenge);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @Valid @RequestBody Challenge challenge){
		Challenge savedChallenge = challengeService.updateChallenge(id, challenge);
		return ResponseEntity.ok(savedChallenge);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteChallenge(@PathVariable Long id) {
		challengeRepository.deleteById(id);
	}

}
