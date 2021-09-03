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

import com.gft.restapi.controllers.DTO.ChallengeDTO;
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
	public List<ChallengeDTO> listChallenges(){
		
		List<ChallengeDTO> challengeDTOList = new ArrayList<>();
		List<Challenge> challengeList = challengeRepository.findAll();
		
		for(Challenge challenge : challengeList) {
			ChallengeDTO challengeDTO = ChallengeDTO.from(challenge);
			challengeDTOList.add(challengeDTO);
		}
		return challengeDTOList;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeDTO> findChallengById(@PathVariable Long id){
		Optional<Challenge> foundedChallenge = challengeRepository.findById(id);
		ChallengeDTO challengeDTO = ChallengeDTO.from(foundedChallenge);
		return !foundedChallenge.isEmpty() ? ResponseEntity.ok(challengeDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeDTO> createChallenge (@Valid @RequestBody Challenge challenge, HttpServletResponse response){
		Challenge savedChallenge = challengeRepository.save(challenge);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedChallenge.getId()));
		ChallengeDTO challengeDTO = ChallengeDTO.from(savedChallenge);
		return ResponseEntity.status(HttpStatus.CREATED).body(challengeDTO);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ChallengeDTO> updateChallenge(@PathVariable Long id, @Valid @RequestBody Challenge challenge){
		Challenge savedChallenge = challengeService.updateChallenge(id, challenge);
		ChallengeDTO challengeDTO = ChallengeDTO.from(savedChallenge);
		return ResponseEntity.ok(challengeDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteChallenge(@PathVariable Long id) {
		challengeRepository.deleteById(id);
	}

}
