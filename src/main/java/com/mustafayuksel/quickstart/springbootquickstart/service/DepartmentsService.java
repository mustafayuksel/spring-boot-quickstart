package com.mustafayuksel.quickstart.springbootquickstart.service;

import com.mustafayuksel.quickstart.springbootquickstart.response.FindDepartmentsResponse;

public interface DepartmentsService {
	FindDepartmentsResponse findByName(String name);
}