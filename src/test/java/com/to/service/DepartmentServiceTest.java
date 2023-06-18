package com.to.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.to.entity.Department;
import com.to.repository.DepartmentRepo;

@SpringBootTest
public class DepartmentServiceTest {
	
	@Mock
	DepartmentRepo departmentRepo;
	
	@InjectMocks
	DepartmentService departmentService;

	@Test
	@DisplayName("Test case for testing the saveDepartment method")
	void testSaveDepartment() {
	     Department department=Department.builder().departmentName("IT")
	    		 .departmentAddress("patas")
	    		 .departmentCode("IT-01")
	    		 .build();
	     Department savedDepartment=Department.builder().departmentName("IT")
	    		 .departmentAddress("patas")
	    		 .departmentCode("IT-01")
	    		 .departmentId(1l)
	    		 .build();
	     
	     when(departmentRepo.save(department)).thenReturn(savedDepartment);
	     //now check with the assert
	     Department actualSavedDepartment=departmentService.saveDepartment(department);
	     assertNotNull(actualSavedDepartment);
	     assertEquals(savedDepartment, actualSavedDepartment);
	}
	
	@Test
	@DisplayName("Test for testing the get All departments")
	void testGetAllDepartments() {
		when(departmentRepo.findAll()).thenReturn(Stream.of(Department.builder().departmentName("IT")
	    		 .departmentAddress("patas")
	    		 .departmentCode("IT-01")
	    		 .departmentId(1l)
	    		 .build()).toList());
		assertEquals(1, departmentService.getAllDepartments().size());
	}
	
	@Test
	@DisplayName("Test for get Department by id")
	void testGetDepartmentById() {
	     Department savedDepartmentWithId1=Department.builder().departmentName("IT")
	    		 .departmentAddress("patas")
	    		 .departmentCode("IT-01")
	    		 .departmentId(1l)
	    		 .build();
		when(departmentRepo.findById(1l)).thenReturn(Optional.of(savedDepartmentWithId1));
		assertNotNull(departmentService.getDepartmentById(1l));
	}
	
	@Test
	@DisplayName("Delete department test")
	void testDeleteDepartment() {
		 Department dummyDepartment=Department.builder().departmentName("IT")
	    		 .departmentAddress("patas")
	    		 .departmentCode("IT-01")
	    		 .departmentId(1l)
	    		 .build();
		//return the department because in delete method we are calling the find by id method 
		when(departmentRepo.findById(dummyDepartment.getDepartmentId())).thenReturn(Optional.of(dummyDepartment));
		departmentService.deleteDepartment(1l);
		verify(departmentRepo,times(1)).delete(dummyDepartment);          	
	}
}
