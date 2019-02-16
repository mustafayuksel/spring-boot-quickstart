package com.mustafayuksel.quickstart.springbootquickstart.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class MediatorRequest {

    @ApiModelProperty
    @NotBlank(message = "Request may not be blank")
    private String request;
    @ApiModelProperty
    private int timeOut;
    @ApiModelProperty
    @NotBlank(message = "Endpoint may not be blank")
    private String endpoint;

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

    public MediatorRequest(String request, int timeOut, String endpoint) {
        this.request = request;
        this.timeOut = timeOut;
        this.endpoint = endpoint;
    }
}