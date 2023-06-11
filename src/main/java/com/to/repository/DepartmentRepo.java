package com.to.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entity.Department;

public interface DepartmentRepo  extends JpaRepository<Department, Long>{

}
