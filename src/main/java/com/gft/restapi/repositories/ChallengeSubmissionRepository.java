package com.gft.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.restapi.entities.ChallengeSubmission;

@Repository
public interface ChallengeSubmissionRepository extends JpaRepository<ChallengeSubmission, Long>{

}
