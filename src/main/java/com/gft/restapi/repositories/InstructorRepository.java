package com.gft.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.restapi.entities.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}
