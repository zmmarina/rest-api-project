package com.gft.restapi.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
	@NotEmpty(message= "A street must be informed.")
	@Size(min=4, max=100, message = "It must has between 4 and 100 letters.")
	private String street;
	
	@NotEmpty(message= "A number must be informed.")
	@Size(min=4, max=100, message = "It must has between 4 and 100 letters.")
	private String number;
	
	@NotEmpty(message= "A city must be informed.")
	@Size(min=4, max=100, message = "It must has between 4 and 100 letters.")
	private String city;
	
	@NotEmpty(message= "A state must be informed.")
	@Size(min=4, max=100, message = "It must has between 4 and 100 letters.")
	private String state;
	
	@NotEmpty(message= "A district must be informed.")
	@Size(min=4, max=100, message = "It must has between 4 and 100 letters.")
	private String district;

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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	

}
