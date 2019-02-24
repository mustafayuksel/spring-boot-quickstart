package com.mustafayuksel.quickstart.springbootquickstart.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mustafayuksel.quickstart.springbootquickstart.domain.Departments;

public interface DepartmentsRepository extends MongoRepository<Departments, String> {
	@Cacheable("findDepartmentsByNameContaining")
	public List<Departments> findByNameContaining(String name);
}