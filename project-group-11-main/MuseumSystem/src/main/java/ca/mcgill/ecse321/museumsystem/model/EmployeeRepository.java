package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.Account;

public interface EmployeeRepository extends CrudRepository<Employee, String>{
	Employee findEmployeeByAccountID(int accountID);

}
