package com.gft.restapi.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gft.restapi.entities.Instructor;
import com.gft.restapi.entities.User;
import com.gft.restapi.repositories.InstructorRepository;

@Service
public class InstructorService {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private UserFactoryService userFactoryService;
	
	public Instructor updateInstructor(Long id, Instructor instructor) {
		Instructor foundedInstructor = findInstructorById(id);
		BeanUtils.copyProperties(instructor, foundedInstructor, "id");
		return instructorRepository.save(foundedInstructor);
	}
	
	public Instructor findInstructorById(Long id) {
		Optional<Instructor> foundedInstructor = instructorRepository.findById(id);
		
		if(foundedInstructor.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return foundedInstructor.get();
	}
	
	@Transactional
	public Instructor saveInstructor(Instructor instructor) {
		Instructor instructor2 = instructorRepository.save(instructor);
		User user = userFactoryService.createUser(instructor2);
		instructor2.setUser(user);
		
		instructorRepository.save(instructor2);
		
		return instructor2;
	}

}
