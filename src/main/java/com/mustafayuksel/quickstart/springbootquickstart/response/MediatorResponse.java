package com.mustafayuksel.quickstart.springbootquickstart.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MediatorResponse {

	@ApiModelProperty
	private Boolean isSuccess;
	@ApiModelProperty
	private String response;

	public Boolean getSuccess() {
		return isSuccess;
	}

	public String getResponse() {
		return response;
	}

	public MediatorResponse(String response, Boolean isSuccess) {
		this.response = response;
		this.isSuccess = isSuccess;
	}
}