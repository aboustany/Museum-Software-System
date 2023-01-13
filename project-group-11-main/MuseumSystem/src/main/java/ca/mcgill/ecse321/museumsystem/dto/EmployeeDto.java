package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.Employee;


/**
 * 
 * @author Karim
 * Data transfer object for Employee class.
 * @param int id
 * @param String username
 *
 */
public class EmployeeDto {
	
	private int id;
	
	private String username;
	
	private String password;
	
	public EmployeeDto(Employee employee) {
		if(employee != null) {
			this.id = employee.getAccountID();
			this.username = employee.getUserName();
			this.password = employee.getPassword();
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUserName() {
		return this.username;
	}

}
