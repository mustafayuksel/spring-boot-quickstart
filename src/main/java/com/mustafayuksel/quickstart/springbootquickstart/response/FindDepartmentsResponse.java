package com.mustafayuksel.quickstart.springbootquickstart.response;

import java.util.List;

import com.mustafayuksel.quickstart.springbootquickstart.dto.DepartmentsDTO;

import io.swagger.annotations.ApiModel;

@ApiModel
public class FindDepartmentsResponse {
	private List<DepartmentsDTO> departmentsDTO;

	public List<DepartmentsDTO> getDepartmentsDTO() {
		return departmentsDTO;
	}

	public FindDepartmentsResponse(List<DepartmentsDTO> departmentsDTO) {
		this.departmentsDTO = departmentsDTO;
	}
}