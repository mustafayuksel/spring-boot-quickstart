package com.mustafayuksel.quickstart.springbootquickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mustafayuksel.quickstart.springbootquickstart.response.FindDepartmentsResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.DepartmentsService;

@RestController
@RequestMapping("/departments")
public class DepartmentsRestController {

	private final DepartmentsService departmentsService;

	@Autowired
	public DepartmentsRestController(DepartmentsService departmentsService) {
		this.departmentsService = departmentsService;
	}

	@GetMapping(value = "/{name}")
	public FindDepartmentsResponse findByName(@PathVariable("name") String name) {
		return departmentsService.findByName(name);
	}
}