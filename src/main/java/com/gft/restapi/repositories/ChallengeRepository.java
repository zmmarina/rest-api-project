package com.gft.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.restapi.entities.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge,Long>{

}
