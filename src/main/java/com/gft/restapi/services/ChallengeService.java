package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.restapi.entities.Challenge;
import com.gft.restapi.repositories.ChallengeRepository;

@Service
public class ChallengeService {
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	public Challenge updateChallenge(Long id, Challenge challenge) {
		Challenge foundedChallenge = findChallengeById(id);
		BeanUtils.copyProperties(challenge, foundedChallenge, "id");
		return challengeRepository.save(foundedChallenge);
	}
	
	public Challenge findChallengeById(Long id) {
		Optional<Challenge> foundedChallenge = challengeRepository.findById(id);
		if(foundedChallenge.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return foundedChallenge.get();
	}

}
