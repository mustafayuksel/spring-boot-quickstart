package com.mustafayuksel.quickstart.springbootquickstart.service;

import com.mustafayuksel.quickstart.springbootquickstart.response.ListUserResponse;

public interface UserService {

	ListUserResponse findAll();
}