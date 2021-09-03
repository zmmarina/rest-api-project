package com.gft.restapi.controllers;

import java.util.ArrayList;
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

import com.gft.restapi.controllers.DTO.InstructorDTO;
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
	public List<InstructorDTO> listInstructors(){
		List<InstructorDTO> instructorDTOList = new ArrayList<>();
		List<Instructor> instructorList = instructorRepository.findAll();
		
		for(Instructor instructor : instructorList) {
			InstructorDTO instructorDTO = InstructorDTO.from(instructor);
			instructorDTOList.add(instructorDTO);
		}
		return instructorDTOList;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<InstructorDTO> findInstructorById(@PathVariable Long id){
		Optional<Instructor> foundedInstructor = instructorRepository.findById(id);
		InstructorDTO instructorDTO = InstructorDTO.from(foundedInstructor);
		return !foundedInstructor.isEmpty() ? ResponseEntity.ok(instructorDTO) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<InstructorDTO> createInstructor(@Valid @RequestBody Instructor instructor, HttpServletResponse response){
		Instructor savedInstructor = instructorService.saveInstructor(instructor);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedInstructor.getId()));
		InstructorDTO instructorDTO = InstructorDTO.from(savedInstructor);
		return ResponseEntity.status(HttpStatus.CREATED).body(instructorDTO);		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructor){
		Instructor savedInstructor = instructorService.updateInstructor(id, instructor);
		InstructorDTO instructorDTO = InstructorDTO.from(savedInstructor);
		return ResponseEntity.ok(instructorDTO);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInstructor(@PathVariable Long id) {
		instructorRepository.deleteById(id);
	}

}
