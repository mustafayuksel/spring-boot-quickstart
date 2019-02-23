package com.mustafayuksel.quickstart.springbootquickstart.response;

import java.util.List;

import com.mustafayuksel.quickstart.springbootquickstart.dto.UserDTO;

import io.swagger.annotations.ApiModel;

@ApiModel
public class ListUserResponse {
	private List<UserDTO> userDTOs;
	
	public List<UserDTO> getUserDTOs() {
		return userDTOs;
	}

	public ListUserResponse(List<UserDTO> userDTOs) {
		this.userDTOs = userDTOs;
	}
}