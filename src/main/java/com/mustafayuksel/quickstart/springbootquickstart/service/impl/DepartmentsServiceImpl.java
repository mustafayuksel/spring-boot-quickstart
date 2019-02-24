package com.mustafayuksel.quickstart.springbootquickstart.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.quickstart.springbootquickstart.domain.Departments;
import com.mustafayuksel.quickstart.springbootquickstart.repository.DepartmentsRepository;
import com.mustafayuksel.quickstart.springbootquickstart.response.FindDepartmentsResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.DepartmentsService;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {

	private final DepartmentsRepository departmentsRepository;

	@Autowired
	public DepartmentsServiceImpl(DepartmentsRepository departmentsRepository) {
		this.departmentsRepository = departmentsRepository;
	}

	@Override
	public FindDepartmentsResponse findByName(String name) {
		return new FindDepartmentsResponse(departmentsRepository.findByNameContaining(name).stream()
				.map(Departments::convertToDto).collect(Collectors.toList()));
	}
}