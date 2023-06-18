package com.to.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.to.entity.Department;
import com.to.repository.DepartmentRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {

	@Autowired
	DepartmentRepo repo;
	
	public Department saveDepartment(Department department) {
		log.info("Insid the DepartmentService saveDepartment method");
		return repo.save(department);
	}

	public Department getDepartmentById(Long depId) {
		log.info("Inside the DepartmentService getDepartmentById method");
		return repo.findById(depId).get();
	}

	public List<Department> getAllDepartments() {
		log.info("Inside the DepartmentService getAllDepartments method");
		return repo.findAll();
	} 
	
	public void deleteDepartment(Long depId) {
		log.info("Inside the DepartmentService deleteDepartment method");
		Department dbDepartment=repo.findById(depId).get();
		repo.delete(dbDepartment);
		log.info("Department with the id"+depId+" deleted successfully");
	}
}
