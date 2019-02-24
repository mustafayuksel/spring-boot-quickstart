package com.mustafayuksel.quickstart.springbootquickstart.domain;

import javax.persistence.Id;

import org.bson.types.ObjectId;

import com.mustafayuksel.quickstart.springbootquickstart.dto.DepartmentsDTO;

public class Departments {
	@Id
	public ObjectId _id;
	public String name;

	public Departments() {
	}

	public Departments(ObjectId _id, String name) {
		this._id = _id;
		this.name = name;
	}

	public DepartmentsDTO convertToDto() {
		DepartmentsDTO departmentsDTO = new DepartmentsDTO();
		departmentsDTO.setId(this._id);
		departmentsDTO.setName(this.name);
		return departmentsDTO;
	}
}