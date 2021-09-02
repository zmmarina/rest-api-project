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

import com.gft.restapi.entities.ChallengeSubmission;
import com.gft.restapi.events.CreatedResourceEvent;
import com.gft.restapi.repositories.ChallengeSubmissionRepository;
import com.gft.restapi.services.ChallengeSubmissionService;

@RestController
@RequestMapping("/challengesubmission")
public class ChallengeSubmissionController {
	
	@Autowired
	private ChallengeSubmissionRepository challengeSubmissionRepository;
	
	@Autowired
	private ChallengeSubmissionService challengeSubmissionService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<ChallengeSubmission> listChallengeSubmissions(){
		return challengeSubmissionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Optional<ChallengeSubmission>> findChallengeSubmissionById(@PathVariable Long id){
		Optional<ChallengeSubmission> foundedChallengeSubmission = challengeSubmissionRepository.findById(id);
		return !foundedChallengeSubmission.isEmpty() ? ResponseEntity.ok(foundedChallengeSubmission) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ChallengeSubmission> createChallengeSubmission(@Valid @RequestBody ChallengeSubmission challengeSubmission, HttpServletResponse response){
		ChallengeSubmission savedChallengeSubmission = challengeSubmissionRepository.save(challengeSubmission);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedChallengeSubmission.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedChallengeSubmission);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeSubmission> updateChallengeSubmission(@PathVariable Long id, @Valid @RequestBody ChallengeSubmission challengeSubmission){
		ChallengeSubmission savedChallengeSubmission = challengeSubmissionService.updateChallengeSubmission(id, challengeSubmission);
		return ResponseEntity.ok(savedChallengeSubmission);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteChallengeSubmission(@PathVariable Long id) {
		challengeSubmissionRepository.deleteById(id);
	}		

}
