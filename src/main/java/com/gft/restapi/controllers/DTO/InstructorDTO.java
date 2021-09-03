package com.gft.restapi.controllers.DTO;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gft.restapi.entities.Instructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InstructorDTO {
	
	private Long id;	
	private String name;
	private String email;
	private String phone;
	private String street;
	private String number;
	private String district;
	private String city;
	private String state;
	
	
	public InstructorDTO() {}

	public InstructorDTO(Long id, String name, String email, String phone, String street, String number,
			String district, String city, String state) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.number = number;
		this.district = district;
		this.city = city;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public static InstructorDTO from(Optional<Instructor> foundedInstructor) {
		return new InstructorDTO(foundedInstructor.get().getId(), foundedInstructor.get().getName(), foundedInstructor.get().getEmail(),
				foundedInstructor.get().getPhone(), foundedInstructor.get().getAddress().getStreet(), foundedInstructor.get().getAddress().getNumber(), 
				foundedInstructor.get().getAddress().getDistrict(), foundedInstructor.get().getAddress().getCity(), foundedInstructor.get().getAddress().getState());
	}
	
	public static InstructorDTO from(Instructor instructor) {
		return new InstructorDTO(instructor.getId(), instructor.getName(), instructor.getEmail(),
				instructor.getPhone(), instructor.getAddress().getStreet(), instructor.getAddress().getNumber(), 
				instructor.getAddress().getDistrict(), instructor.getAddress().getCity(), instructor.getAddress().getState());
	}		

}
