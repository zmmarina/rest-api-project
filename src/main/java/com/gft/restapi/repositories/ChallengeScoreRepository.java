package com.gft.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.restapi.entities.ChallengeScore;

@Repository
public interface ChallengeScoreRepository extends JpaRepository<ChallengeScore, Long>{

}
