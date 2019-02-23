package com.mustafayuksel.quickstart.springbootquickstart.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.quickstart.springbootquickstart.domain.User;
import com.mustafayuksel.quickstart.springbootquickstart.repository.UserRepository;
import com.mustafayuksel.quickstart.springbootquickstart.response.ListUserResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ListUserResponse findAll() {
		return new ListUserResponse(
				userRepository.findAll().stream().map(User::convertToDto).collect(Collectors.toList()));
	}
}