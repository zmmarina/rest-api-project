package com.gft.restapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;

@Entity
public class ChallengeScore {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="challengesubmission_id")
	private ChallengeSubmission challengeSubmission;
	
	@Range(min= 1, max=3, message="Score must be between 1 and 3.")
	private Integer codeQualityScore;
	
	@Range(min= 1, max=3, message="Score must be between 1 and 3.")
	private Integer deliveredQuantityScore;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChallengeSubmission getChallengeSubmission() {
		return challengeSubmission;
	}

	public void setChallengeSubmission(ChallengeSubmission challengeSubmission) {
		this.challengeSubmission = challengeSubmission;
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

}
