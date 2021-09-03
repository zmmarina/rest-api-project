package com.gft.restapi.controllers.DTO;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gft.restapi.entities.Challenge;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChallengeDTO {
	
	private Long id;
	private String name;
	
	
	public ChallengeDTO() {	}


	public ChallengeDTO(Long id, String name) {	
		this.id = id;
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public static ChallengeDTO from(Optional<Challenge> foundedChallenge) {
		return new ChallengeDTO(foundedChallenge.get().getId(), foundedChallenge.get().getName());
	}
	
	public static ChallengeDTO from(Challenge challenge) {
		return new ChallengeDTO(challenge.getId(), challenge.getName());
	}
	

}
