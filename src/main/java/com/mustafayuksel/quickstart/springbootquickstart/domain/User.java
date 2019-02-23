package com.mustafayuksel.quickstart.springbootquickstart.domain;

import javax.persistence.Entity;

import com.mustafayuksel.quickstart.springbootquickstart.dto.UserDTO;

@Entity
public class User extends BaseEntity {

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

	private String name;

	private String email;

	public UserDTO convertToDto() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(getId());
		userDTO.setName(getName());
		userDTO.setEmail(getEmail());
		return userDTO;
	}
}