package com.gft.restapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.restapi.entities.Instructor;
import com.gft.restapi.events.CreatedResourceEvent;
import com.gft.restapi.repositories.InstructorRepository;
import com.gft.restapi.services.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Instructor> listInstructors(){
		return instructorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Optional<Instructor>> findInstructorById(@PathVariable Long id){
		Optional<Instructor> foundedInstructor = instructorRepository.findById(id);
		return !foundedInstructor.isEmpty() ? ResponseEntity.ok(foundedInstructor) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor, HttpServletResponse response){
		Instructor savedInstructor = instructorService.saveInstructor(instructor);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedInstructor.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructor);		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructor){
		Instructor savedInstructor = instructorService.updateInstructor(id, instructor);
		return ResponseEntity.ok(savedInstructor);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInstructor(@PathVariable Long id) {
		instructorRepository.deleteById(id);
	}

}
