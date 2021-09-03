package com.gft.restapi.controllers.DTO;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gft.restapi.entities.Starter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StarterDTO {
	
	private Long id;	
	private String name;
	private String letters;
	private String language;
	private String email;
	private String phone;
	private String street;
	private String number;
	private String district;
	private String city;
	private String state;
	
	public StarterDTO() {}
	
	
	public StarterDTO(Long id, String name, String letters, String language, String email, String phone, String street,
			String number, String district, String city, String state) {
		this.id = id;
		this.name = name;
		this.letters = letters;
		this.language = language;
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

	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public static StarterDTO from(Optional<Starter> foundedStarter) {
		return new StarterDTO(foundedStarter.get().getId(), foundedStarter.get().getName(), foundedStarter.get().getLetters(), foundedStarter.get().getLanguage(), foundedStarter.get().getEmail(),
				foundedStarter.get().getPhone(), foundedStarter.get().getAddress().getStreet(), foundedStarter.get().getAddress().getNumber(), 
				foundedStarter.get().getAddress().getDistrict(), foundedStarter.get().getAddress().getCity(), foundedStarter.get().getAddress().getState());
	}
	
	public static StarterDTO from(Starter starter) {
		return new StarterDTO(starter.getId(), starter.getName(), starter.getLetters(), starter.getLanguage(), starter.getEmail(),
				starter.getPhone(), starter.getAddress().getStreet(), starter.getAddress().getNumber(), 
				starter.getAddress().getDistrict(), starter.getAddress().getCity(), starter.getAddress().getState());
	}	

}
