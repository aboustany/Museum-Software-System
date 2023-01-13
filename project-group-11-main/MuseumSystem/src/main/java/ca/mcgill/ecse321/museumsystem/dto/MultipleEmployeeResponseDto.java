package ca.mcgill.ecse321.museumsystem.dto;

import java.util.ArrayList;

import ca.mcgill.ecse321.museumsystem.model.Employee;

public class MultipleEmployeeResponseDto {
	
	private Iterable<Employee> employees;
	
	public MultipleEmployeeResponseDto(Iterable<Employee> employees) {
		ArrayList<Employee> employee = new ArrayList<Employee>();
		for (Employee e : employees) {
			employee.add(e);
		}
		this.employees = employee;
	}
	
	public Iterable<Employee> getEmployees() {
		return this.employees;
	}

}
