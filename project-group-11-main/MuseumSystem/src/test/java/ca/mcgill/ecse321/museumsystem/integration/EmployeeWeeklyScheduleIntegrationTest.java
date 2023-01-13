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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklyScheduleRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeWeeklyScheduleIntegrationTest {
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private EmployeeWeeklyScheduleRepository scheduleRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private OwnerRepository ownerRepo;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		scheduleRepo.deleteAll();
		employeeRepo.deleteAll();
		ownerRepo.deleteAll();
	}
	
	@Test
	public void testCreateAndGetEmployeeWeeklySchedule() {
		int id = testCreateEmployeeWeeklySchedule();
		testGetExistingEmployeeWeeklyScheduleById(id);
	}
	
	private int testCreateEmployeeWeeklySchedule() {
		Employee employee1 = new Employee();
		//employee1.setUserName("marwan");
		//employee1.setPassword("password1");
		Owner owner1 = new Owner();
		//owner1.setUserName("louis");
		//owner1.setPassword("password2");
		EmployeeWeeklySchedule schedule = new EmployeeWeeklySchedule();
		schedule.setEmployee(employee1);
		schedule.setOwner(owner1);
		schedule.setStartDay("01/01/2023");
		schedule.setEndDay("07/01/2023");
		ResponseEntity<EmployeeWeeklySchedule> response = client.postForEntity("/employee_weekly_schedule", schedule, EmployeeWeeklySchedule.class);

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("01/01/2023", response.getBody().getStartDay(), "Response has correct schedule start date");
		assertEquals("07/01/2023", response.getBody().getStartDay(), "Response has correct schedule end date");
		assertNotNull(response.getBody().getScheduleID(), "Response has valid ID");
		
		return response.getBody().getScheduleID();
	}
	
	private void testGetExistingEmployeeWeeklyScheduleById(int id) {
		ResponseEntity<EmployeeWeeklySchedule> response = client.getForEntity("/employee_weekly_schedule/" + id, EmployeeWeeklySchedule.class);
		
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("01/01/2023", response.getBody().getStartDay(), "Response has correct schedule start date");
		assertEquals("07/01/2023", response.getBody().getStartDay(), "Response has correct schedule end date");
		assertTrue(response.getBody().getScheduleID() == id, "Response has correct ID");
	}


}

