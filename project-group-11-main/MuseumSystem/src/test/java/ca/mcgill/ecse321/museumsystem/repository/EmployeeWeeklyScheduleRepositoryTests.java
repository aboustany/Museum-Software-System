package ca.mcgill.ecse321.museumsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklyScheduleRepository;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;

@SpringBootTest
public class EmployeeWeeklyScheduleRepositoryTests {
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private EmployeeWeeklyScheduleRepository employeeWeeklyScheduleRepository; 
	
	@Autowired
	private EmployeeRepository employeeRepository; 
	
	@Autowired
	private MuseumSystemRepository museumSystemRepository; 
	
	/**
	 * deleteEntries is a method used to clear our database
	 * 
	 * @author ElsaChelala
	 * */
	@AfterEach
	public void deleteEntries() {
		employeeWeeklyScheduleRepository.deleteAll();
		employeeRepository.deleteAll();
		ownerRepository.deleteAll();
		museumSystemRepository.deleteAll();
	}
	
	/*
	 * testPersistEmployeeWeeklySchedule is a method used to create an employee schedule, store it in our database 
	 * and check whether it has been correctly stores.
	 * 
	 * @author ElsaChelala
	 * 
	 * */
	
	@Test
	public void testPersistEmployeeWeeklySchedule() {
		MuseumSystem museum = new MuseumSystem();
		
		Owner owner = new Owner();
		String userName = "SamiF";
		String password = "sucks";
		owner.setUserName(userName);
		owner.setPassword(password);
		ownerRepository.save(owner);
		
//		museum.setOwner(owner);
//		museumSystemRepository.save(museum);
		
		Employee employee = new Employee();
		String employeeUsername = "KarimK";
		String employeePassword = "Goat";
		employee.setUserName(employeeUsername);
		employee.setPassword(employeePassword);
		//employee.setAccountID(1);
		employee=employeeRepository.save(employee);
		
		EmployeeWeeklySchedule schedule = new EmployeeWeeklySchedule(owner, employee);
		String startDate = "01/01/2020";
		String endDate = "01/01/2030";
		schedule.setStartDay(startDate);
		schedule.setEndDay(endDate);
		schedule = employeeWeeklyScheduleRepository.save(schedule);
		
		int scheduleId = schedule.getScheduleID();
		
		EmployeeWeeklySchedule tester = employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(scheduleId);
		assertNotNull(tester);
		assertEquals(scheduleId, tester.getScheduleID());
		assertEquals(startDate, tester.getStartDay());
		assertEquals(endDate, tester.getEndDay());
		
	}
	
}
