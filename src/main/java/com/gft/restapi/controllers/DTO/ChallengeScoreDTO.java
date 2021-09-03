package com.gft.restapi.controllers.DTO;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gft.restapi.entities.ChallengeScore;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ChallengeScoreDTO {
	
	private Long id;
	private Integer codeQualityScore;
	private Integer deliveredQuantityScore;
	
	
	public ChallengeScoreDTO() {}

	public ChallengeScoreDTO(Long id, Long submissionId, Integer codeQualityScore,
			Integer deliveredQuantityScore) {
		this.id = id;
		this.codeQualityScore = codeQualityScore;
		this.deliveredQuantityScore = deliveredQuantityScore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodeQualityScore() {
		return codeQualityScore;
	}

	public void setCodeQualityScore(Integer codeQualityScore) {
		this.codeQualityScore = codeQualityScore;
	}

	public Integer getDeliveredQuantityScore() {
		return deliveredQuantityScore;
	}

	public void setDeliveredQuantityScore(Integer deliveredQuantityScore) {
		this.deliveredQuantityScore = deliveredQuantityScore;
	}	
	
	public static ChallengeScoreDTO from(Optional<ChallengeScore> foundedChallengeScore) {
		return new ChallengeScoreDTO();
	}
	
	public static ChallengeScoreDTO from(ChallengeScore challengeScore) {
		return new ChallengeScoreDTO(challengeScore.getId(), challengeScore.getChallengeSubmission().getId(),				
				challengeScore.getCodeQualityScore(), challengeScore.getDeliveredQuantityScore());
	}

}
