package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

 

import com.cg.dao.EmployeeRepo;
import com.cg.entity.Account;
import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeRepo repo;

	@InjectMocks
	private EmployeeServiceImpl eService;

	private List<Employee> employeeList;

	private Employee emp=null;

	 

	private EmployeeServiceImplTest() {

	eService=new EmployeeServiceImpl();

	employeeList=new ArrayList<>();

	}

	@BeforeEach

	private void initEach() {

	emp=new Employee();

	emp.setEmpId(123);

	emp.setEmpName("Sayali");

	emp.setEmpSalary(90000);

	Account acc = new Account();

	acc.setAccNo(23456);

	acc.setAccType("Seving");

	emp.setAccount(acc);

	 

	employeeList.add(emp);

	}

	 

	@Test

	void addEmployeeTest() {

	Mockito.when(eService.addEmployee(emp)).thenReturn(emp);

	assertEquals(eService.addEmployee(emp), emp);

	//verify(repo,Mockito.times(1).save(emp));

	}

	 

	@Test

	void findAllEmployeeTest() {

	Mockito.when(eService.findAllEmployee()).thenReturn(employeeList);

	assertEquals(eService.findAllEmployee(), employeeList);

	}
	@Test

	void findEmployeeById() {

	try {

	Optional<Employee> emplo = Optional.of(emp);

	Mockito.when(repo.findById(123)).thenReturn(emplo);

	assertEquals(emplo.get(), eService.findEmployeeById(123));

	} catch (NoSuchEmployeeFoundException e) {

	e.printStackTrace();

	fail("No such Employee Found Exception");

	}

	}

	 

	@Test

	void modifyByEmployeeById() {

	emp.setEmpName("Priti");

	try {

	Mockito.when(eService.modifyByEmployeeId(emp, 123)).thenReturn(emp);

	} catch (NoSuchEmployeeFoundException e) {

	}

	try {

	assertEquals(emp, eService.modifyByEmployeeId(emp, 123));

	 

	} catch (NoSuchEmployeeFoundException e) {

	}

	}

	 

	@Test

	void removeEmployee() throws NoSuchEmployeeFoundException {

	boolean isDelete = eService.removeEmployee(123);

	assertTrue(isDelete);

	}
	 
	

}
