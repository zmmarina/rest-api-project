package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.restapi.entities.ChallengeSubmission;
import com.gft.restapi.repositories.ChallengeSubmissionRepository;

@Service
public class ChallengeSubmissionService {
	
	@Autowired
	private ChallengeSubmissionRepository challengeSubmissionRepository;
	
	
	public ChallengeSubmission updateChallengeSubmission(Long id, ChallengeSubmission challengeSubmission) {
		ChallengeSubmission foundedChallengeSubmission = findChallengeSubmissionById(id);
		BeanUtils.copyProperties(challengeSubmission, foundedChallengeSubmission, "id");
		return challengeSubmissionRepository.save(foundedChallengeSubmission);
	}
	
	public ChallengeSubmission findChallengeSubmissionById(Long id) {
		Optional<ChallengeSubmission> foundedChallengeSubmission = challengeSubmissionRepository.findById(id);
		if(foundedChallengeSubmission.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return foundedChallengeSubmission.get();	
	}

}
