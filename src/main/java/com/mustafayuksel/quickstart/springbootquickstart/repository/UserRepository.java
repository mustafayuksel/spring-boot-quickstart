package com.mustafayuksel.quickstart.springbootquickstart.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mustafayuksel.quickstart.springbootquickstart.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	@Cacheable("findAllUsers")
	public List<User> findAll();
}