package com.to.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentController {
 
	 @Autowired
	 private DepartmentService service;
	 
	 @PostMapping
	 public Department saveDepartment(@RequestBody Department department) {
		 log.info("Inside the saveDepartment method of DepartmentCotroller");
		 return service.saveDepartment(department);
	 }
	 
	@GetMapping("/{depId}")
	public Department getDepartmentById(@PathVariable Long depId) {
		 log.info("Inside the getDepartmentById method of DepartmentCotroller");
		return service.getDepartmentById(depId);
	}
	
	@GetMapping
	public List<Department> getAllDepartments(){
		 log.info("Inside the getAllDepartments method of DepartmentCotroller");
		return service.getAllDepartments();
	}
	
	 
}
