package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.restapi.entities.ChallengeScore;
import com.gft.restapi.repositories.ChallengeScoreRepository;

@Service
public class ChallengeScoreService {
	
	@Autowired
	private ChallengeScoreRepository challengeScoreRepository;
	
	public ChallengeScore updateChallengeScore(Long id, ChallengeScore challengeScore) {
		ChallengeScore foundedChallengeScore = findChallengeScoreById(id);
		BeanUtils.copyProperties(challengeScore, foundedChallengeScore, "id");
		return challengeScoreRepository.save(foundedChallengeScore);
	}
	
	public ChallengeScore findChallengeScoreById(Long id) {
		Optional<ChallengeScore> foundedChallengeScore = challengeScoreRepository.findById(id);
		if(foundedChallengeScore.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return foundedChallengeScore.get();	
	}
	
	

}
