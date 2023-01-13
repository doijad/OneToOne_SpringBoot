package com.cg.service;

import java.util.List;

import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;

public interface EmployeeService {

	public Employee addEmployee(Employee e);
	
	public List<Employee> findAllEmployee();
	
	public Employee findEmployeeById(int id)throws NoSuchEmployeeFoundException;
	
	public Employee modifyByEmployeeId(Employee emp,int id)throws NoSuchEmployeeFoundException;
	
	public boolean removeEmployee(int id)throws NoSuchEmployeeFoundException;
}
