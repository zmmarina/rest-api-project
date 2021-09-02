package com.gft.restapi.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Starter {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message= "A name must be informed.")
	@Size(min=4, max=100, message = "A name must has between 4 and 100 letters.")
	private String name;
	
	@NotEmpty(message= "An email must be informed.")
	private String email;
	
	@NotEmpty(message= "4 letters must be informed.")
	@Size(min=4, max=4, message = "Only 4 letters.")
	private String letters;
	
	@NotEmpty(message= "Telephone number must be informed.")
	private String phone;
	
	@Embedded
	private Address address;
	
	@NotEmpty(message= "A language must be informed.")
	@Size(min=1, max=10, message = "A language must has between 4 and 10 letters.")
	private String language;

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

	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	

}
