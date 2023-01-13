package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.museumsystem.dto.EmployeeDto;
import ca.mcgill.ecse321.museumsystem.dto.MultipleEmployeeResponseDto;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.service.EmployeeService;

/**
 * 
 * @author Karim
 * Controller for Employee class.
 *
 */
@CrossOrigin
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	@GetMapping("/employee")
	public ResponseEntity<MultipleEmployeeResponseDto> getAllEmployees() {
		Iterable<Employee> employees = employeeService.getAllEmployees();
		MultipleEmployeeResponseDto response = new MultipleEmployeeResponseDto(employees);
		return new ResponseEntity<MultipleEmployeeResponseDto>(response, HttpStatus.OK);
	}
	
	@PostMapping(value="/employee", consumes = {"application/json"})
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		employee = employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.getEmployeeById(id);
		employeeService.deleteEmployee(employee);
		return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
	}
	
	
}



