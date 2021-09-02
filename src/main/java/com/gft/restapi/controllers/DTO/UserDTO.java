package com.gft.restapi.controllers.DTO;

public class UserDTO {
	
	private Long id;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserDTO() {
	}
	
	public UserDTO(Long id, String email) {
		this.id = id;
		this.email = email;
	}
	
	

}
