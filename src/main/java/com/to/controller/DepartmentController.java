package com.to.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entity.Department;
import com.to.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/departments")
@Slf4j
@EnableCaching
public class DepartmentController {
 
	 @Autowired
	 private DepartmentService service;
	 
	 @PostMapping
	 @CacheEvict(value = "Departments", allEntries = true) // Invalidate cache after saving a new department
	 public Department saveDepartment(@RequestBody Department department) {
		 log.info("Inside the saveDepartment method of DepartmentCotroller");
		 return service.saveDepartment(department);
	 }
	 
	@GetMapping("/{depId}")
	@Cacheable(value = "Department")
	public Department getDepartmentById(@PathVariable Long depId) {
		 log.info("Inside the getDepartmentById method of DepartmentCotroller");
		return service.getDepartmentById(depId);
	}
	
	@GetMapping
	@Cacheable(value = "Departments")
	public List<Department> getAllDepartments(){
		 log.info("Inside the getAllDepartments method of DepartmentCotroller");
		return service.getAllDepartments();
	}
	
	@DeleteMapping("/{depId}")
    @CacheEvict(value = "Departments", allEntries = true) // Invalidate cache after saving a new department
	public void deleteDepartment(@PathVariable Long depId) {
		log.info("Inside the deleeteDepartment method of DepartmentController");
		service.deleteDepartment(depId);
	}
	 
}
