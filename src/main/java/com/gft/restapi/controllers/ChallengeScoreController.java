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

import com.gft.restapi.controllers.DTO.ChallengeScoreDTO;
import com.gft.restapi.entities.ChallengeScore;
import com.gft.restapi.events.CreatedResourceEvent;
import com.gft.restapi.repositories.ChallengeScoreRepository;
import com.gft.restapi.services.ChallengeScoreService;

@RestController
@RequestMapping("/challengescore")
public class ChallengeScoreController {
	
	@Autowired
	private ChallengeScoreRepository challengeScoreRepository;
	
	@Autowired
	private ChallengeScoreService challengeScoreService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<ChallengeScore> listChallengeScore(){
		return challengeScoreRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeScoreDTO> findChallengeScoreById(@PathVariable Long id){
		Optional<ChallengeScore> foundedChallengeScore = challengeScoreRepository.findById(id);
		ChallengeScoreDTO challengeScoreDTO = ChallengeScoreDTO.from(foundedChallengeScore);
		return !foundedChallengeScore.isEmpty() ? ResponseEntity.ok(challengeScoreDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeScoreDTO> createChallengeScore(@Valid @RequestBody ChallengeScore challengeScore, HttpServletResponse response){
		ChallengeScore savedChallengeScore = challengeScoreRepository.save(challengeScore);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedChallengeScore.getId()));
		ChallengeScoreDTO challengeScoreDTO = ChallengeScoreDTO.from(savedChallengeScore);
		return ResponseEntity.status(HttpStatus.CREATED).body(challengeScoreDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeScoreDTO> updateChallengeScore(@PathVariable Long id, @Valid @RequestBody ChallengeScore challengeScore){
		ChallengeScore savedChallengeScore = challengeScoreService.updateChallengeScore(id, challengeScore);
		ChallengeScoreDTO challengeScoreDTO = ChallengeScoreDTO.from(savedChallengeScore);
		return ResponseEntity.ok(challengeScoreDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteChallengeScore(@PathVariable Long id) {
		challengeScoreRepository.deleteById(id);
	}
	

}
