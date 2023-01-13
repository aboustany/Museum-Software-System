package ca.mcgill.ecse321.museumsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeRepositoryTests {
	@Autowired
	private EmployeeRepository employeeRepository ;

	@AfterEach
	public void deleteEntries() {
		employeeRepository.deleteAll();
	}
	
	/**
	* @author: Mehdi Ammor
	**/
	@Test
	public void testPersistAndLoadEmployee() {
		//Creating employee object and setting random values 
		Employee employee = new Employee();
		employee.setUserName("sami");
		employee.setPassword("sucks");
		//employee.setAccountID(1234);
		//Saving it in database
		employee = employeeRepository.save(employee);
		
		//Testing it
		Employee test = employeeRepository.findEmployeeByAccountID(employee.getAccountID());
		assertNotNull(test);
		//assertEquals(employee.getAccountID(), test.getAccountID());
		assertEquals(employee.getUserName(), test.getUserName());
		assertEquals(employee.getPassword(), test.getPassword());
		
		
	}
	
	

}
