package com.mustafayuksel.quickstart.springbootquickstart.dto;

import org.bson.types.ObjectId;

public class DepartmentsDTO {

	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}