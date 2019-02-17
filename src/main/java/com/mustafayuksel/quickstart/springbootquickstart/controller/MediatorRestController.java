package com.mustafayuksel.quickstart.springbootquickstart.controller;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.MediatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.validation.Valid;

@RestController
@RequestMapping("/mediators")
public class MediatorRestController {
	private final MediatorService mediatorService;

	@Autowired
	public MediatorRestController(MediatorService mediatorService) {
		this.mediatorService = mediatorService;
	}

	@PostMapping(value = "/xml", produces = { "application/xml", "test/xml" }, consumes = { "application/xml" })
	public MediatorResponse callWithXML(@Valid @RequestBody MediatorRequest mediatorRequest) throws IOException {
		return mediatorService.doHttpCall(mediatorRequest);
	}

	@PostMapping(value = "/json")
	public MediatorResponse callWithJSON(@Valid @RequestBody MediatorRequest mediatorRequest) throws IOException {
		return mediatorService.doHttpCall(mediatorRequest);
	}
}