package com.mustafayuksel.quickstart.springbootquickstart.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonIgnore;

@ApiModel
public class MediatorRequest {

	@ApiModelProperty
	private String request;
	@ApiModelProperty
	private int timeOut;
	@ApiModelProperty
	@NotBlank(message = "Endpoint may not be blank")
	private String endpoint;
	private RequestMethod requestMethod;

	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public String getRequest() {
		return request;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public MediatorRequest() {
	}

	public MediatorRequest(String request, int timeOut, String endpoint, RequestMethod requestMethod) {
		this.request = request;
		this.timeOut = timeOut;
		this.endpoint = endpoint;
		this.requestMethod = requestMethod;
	}
	
	@JsonIgnore
	public String getCacheKey() {
		return getEndpoint() + getRequest();
	}
}