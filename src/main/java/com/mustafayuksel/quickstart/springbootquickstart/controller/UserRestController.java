package com.mustafayuksel.quickstart.springbootquickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mustafayuksel.quickstart.springbootquickstart.response.ListUserResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	private final UserService userService;

	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public ListUserResponse findAll() {
		return userService.findAll();
	}
}