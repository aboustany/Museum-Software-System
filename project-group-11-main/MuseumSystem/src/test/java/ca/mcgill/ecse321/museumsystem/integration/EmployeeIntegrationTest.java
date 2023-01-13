package ca.mcgill.ecse321.museumsystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.museumsystem.dto.EmployeeDto;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
/*
 * @author Karim Kanafani
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {
	
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
//	@BeforeEach
//	@AfterEach
//	public void clearDatabase() {
//		employeeRepository.deleteAll();
//	}
	
	
	@Test
	public void testCreateAndGetEmployee() {
		int id = testCreateEmployee();
		testGetEmployee(id);
	}
	
	private int testCreateEmployee() {
		Employee employee1 = new Employee();
		employee1.setUserName("Sami");
		employee1.setPassword("Ferneini");
		ResponseEntity<Employee> response = client.postForEntity("/employee", employee1, Employee.class);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Sami", response.getBody().getUserName(), "Response has correct name");
		assertNotNull(response.getBody().getAccountID(), "Response has valid ID");
		return response.getBody().getAccountID();
	}
	
	private void testGetEmployee(int id) {
		ResponseEntity<Employee> response = client.getForEntity("/employee/" + id, Employee.class);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Sami", response.getBody().getUserName(), "Response has correct name");
		assertTrue(response.getBody().getAccountID() == id, "Response has correct ID");
	}

}
