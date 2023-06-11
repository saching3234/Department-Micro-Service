package com.to.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.to.entity.Department;
import com.to.repository.DepartmentRepo;
import com.to.service.DepartmentService;


//run with is mandatory 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DepartmentControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	ObjectMapper om;
    @MockBean
    DepartmentService service;
   
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	} 

	@Test
	public void addDepartmentTest() throws Exception { 
		System.out.println("\n\n ---------addDepartmentTest method started-----------------------------------------");
		Department department = Department.builder().departmentAddress("mumbai").departmentCode("MECH-01")
				.departmentName("MECH").build();
		Department savedDepartment = Department.builder().departmentAddress("mumbai").departmentCode("MECH-01")
				.departmentName("MECH").departmentId(1l).build();
        //convert the department object into the json string format
		String jsonRequest=om.writeValueAsString(department);
		System.out.println("The json media: "+jsonRequest);
		//when service layer method is called the return the mocked result so that data will not be saved in the db
		when(service.saveDepartment(department)).thenReturn(savedDepartment);
		//now give the post call
		MvcResult result=mockMvc.perform(post("/departments").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
	    System.out.println("The result of post is :"+result.toString());
		//get the Content String from the response
		String resultContent=result.getResponse().getContentAsString();
		System.out.println("rsultContent :"+resultContent.toString());
		//now convert the resultContent into the Department object
		Department responsedDepartment=om.readValue(resultContent, Department.class);
		System.out.println(responsedDepartment);
        assertEquals("MECH", responsedDepartment.getDepartmentName())	;

		System.out.println("---------addDepartmentTest method ended------------------------------------------------\n\n");
	}
	
	@Test
	public void getAllDepartmentsTest() throws Exception {
		System.out.println("\n\n-------------------get departmAll departments test started---------------------------------\n\n");

		Department savedDepartment = Department.builder().departmentAddress("mumbai").departmentCode("MECH-01")
				.departmentName("MECH").departmentId(1l).build();
		//when service layer called then return the mocked department list
		when(service.getAllDepartments()).thenReturn(Stream.of(savedDepartment).toList());
		//now give the get call
		MvcResult result=mockMvc.perform(get("/departments").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
	    System.out.println("The result of post is :"+result.toString());
		//get the Content String from the response
		String resultContent=result.getResponse().getContentAsString();
		System.out.println("rsultContent :"+resultContent.toString());
		//now convert the resultContent into the Department object
	    List<Department> responsedDepartment=om.readValue(resultContent,new TypeReference<List<Department>>() {});
		System.out.println("Responded department list : "+responsedDepartment);
        assertNotNull(responsedDepartment); 
		System.out.println("\n\n-------------------get departmAll departments test ended---------------------------------\n\n");

	}
	
	@Test
	public void getDepartmentByIdTest() throws Exception {
		System.out.println("\n\n-------------------get departm by id test started---------------------------------\n");
		Department savedDepartment = Department.builder().departmentAddress("mumbai").departmentCode("MECH-01")
				.departmentName("MECH").departmentId(1l).build();
		//when service layer called then return the mocked department list
		when(service.getDepartmentById(1l)).thenReturn(savedDepartment);
		//now give the get call
		MvcResult result=mockMvc.perform(get("/departments/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
	    System.out.println("The result of post is :"+result.toString());
		//get the Content String from the response
		String resultContent=result.getResponse().getContentAsString();
		System.out.println("rsultContent :"+resultContent.toString());
		//now convert the resultContent into the Department object
	    Department responsedDepartment=om.readValue(resultContent,Department.class);
		System.out.println("Responded department : "+responsedDepartment);
        assertNotNull(responsedDepartment); 
        System.out.println("\n\n-------------------get departm by id test ended---------------------------------\n");

	}
}
