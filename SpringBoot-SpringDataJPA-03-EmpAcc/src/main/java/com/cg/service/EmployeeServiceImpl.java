package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.EmployeeRepo;
import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo empRepo;
	
	@Override
	@Transactional
	public Employee addEmployee(Employee e) {

		return empRepo.save(e);
	}

	@Override
	public List<Employee> findAllEmployee() {
		
		return empRepo.findAll();
	}

	@Override
	public Employee findEmployeeById(int id) throws NoSuchEmployeeFoundException {
		
		Optional<Employee> emp=empRepo.findById(id);
		if(emp.isPresent())
			return emp.get();
		else
			throw new NoSuchEmployeeFoundException("Employee not found with the id: " + id);
	}

	@Override
	@Transactional
	public Employee modifyByEmployeeId(Employee emp, int id) throws NoSuchEmployeeFoundException {
		
		Employee findEmp = findEmployeeById(id);
		findEmp.setEmpName(emp.getEmpName());
		findEmp.setAccount(emp.getAccount());
		findEmp.setEmpId(emp.getEmpId());
		findEmp.setEmpSalary(emp.getEmpSalary());
		return empRepo.save(findEmp);
	}

	@Override
	@Transactional
	public boolean removeEmployee(int id) throws NoSuchEmployeeFoundException {
		empRepo.deleteById(id);
		Optional<Employee> emp = empRepo.findById(id);
		return !(emp.isPresent());
	}

}
