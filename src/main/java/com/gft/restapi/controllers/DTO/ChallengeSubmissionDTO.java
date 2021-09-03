package com.gft.restapi.controllers.DTO;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gft.restapi.entities.ChallengeSubmission;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChallengeSubmissionDTO {
	
	private Long id;
	private String repositoryLink;
	
	
	public ChallengeSubmissionDTO() {}
	
	public ChallengeSubmissionDTO(Long id, String repositoryLink) {		
		this.id = id;
		this.repositoryLink = repositoryLink;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getRepositoryLink() {
		return repositoryLink;
	}

	public void setRepositoryLink(String repositoryLink) {
		this.repositoryLink = repositoryLink;
	}
	
	public static ChallengeSubmissionDTO from(Optional<ChallengeSubmission> foundedChallengeSubmission) {
		return new ChallengeSubmissionDTO(foundedChallengeSubmission.get().getId(), 
						foundedChallengeSubmission.get().getRepositoryLink());
	}
	
	public static ChallengeSubmissionDTO from(ChallengeSubmission challengeSubmission) {
		return new ChallengeSubmissionDTO(challengeSubmission.getId(), challengeSubmission.getRepositoryLink());
	}
	

}
