package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Employee;
import com.cg.exception.NoSuchEmployeeFoundException;
import com.cg.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/getallemp")
	public ResponseEntity<List<Employee>> getAllEmp(){
		try {
			return new ResponseEntity<>(empService.findAllEmployee(),HttpStatus.OK);
		}
	 catch (Exception ex) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Employee> getById(@PathVariable("id")int id){
		try {
			return new ResponseEntity<>(empService.findEmployeeById(id), HttpStatus.OK);
		} catch (NoSuchEmployeeFoundException ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> createEmp(@RequestBody Employee emp){
		
		try {
			return new ResponseEntity<>(empService.addEmployee(emp),HttpStatus.OK);
		}  catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee newEmp,@PathVariable("id") int id){
		
		try {
			return new ResponseEntity<>(empService.modifyByEmployeeId(newEmp,id), HttpStatus.OK);
		} catch (NoSuchEmployeeFoundException ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable("id") int id){
		try {
			boolean success = empService.removeEmployee(id);
			if(success)
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			else
				return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_MODIFIED);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
	
	
	
	
	
	
	
	
	
}
