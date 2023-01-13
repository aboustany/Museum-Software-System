package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.museumsystem.dto.EmployeeWeeklyScheduleDto;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.service.EmployeeWeeklyScheduleService;

/**
 * 
 * @author Lisa Morcellet
 * 
 * Controller for EmployeeWeeklySchedule class.
 *
 */

@RestController
public class EmployeeWeeklyScheduleController {
	
	@Autowired
	EmployeeWeeklyScheduleService employeeWeeklyScheduleService;
	
	@GetMapping("/employee_weekly_schedule/{id}")
	public ResponseEntity<EmployeeWeeklySchedule> getEmployeeWeeklySchedulebyId(@PathVariable int scheduleID) {
		EmployeeWeeklySchedule employeeWeeklySchedule = employeeWeeklyScheduleService.getEmployeeWeeklyScheduleById(scheduleID);
		return new ResponseEntity<EmployeeWeeklySchedule>(employeeWeeklySchedule, HttpStatus.OK);
	}
	
	@PostMapping("/employee_weekly_schedule")
	public ResponseEntity<EmployeeWeeklySchedule> createEmployeeWeeklySchedule(@RequestBody EmployeeWeeklySchedule employeeWeeklySchedule){
		employeeWeeklySchedule = employeeWeeklyScheduleService.createEmployeeWeeklySchedule(employeeWeeklySchedule);
		return new ResponseEntity<EmployeeWeeklySchedule>(employeeWeeklySchedule, HttpStatus.CREATED);
	} 
	
	@DeleteMapping("/employee_weekly_schedule/{id}")
	public ResponseEntity<String> deleteEmployeeWeeklySchedulebyId(@PathVariable int scheduleID) {
		EmployeeWeeklySchedule employeeWeeklySchedule = employeeWeeklyScheduleService.getEmployeeWeeklyScheduleById(scheduleID);
		employeeWeeklyScheduleService.deleteEmployeeWeeklySchedule(employeeWeeklySchedule);
		return new ResponseEntity<String>("Employee Weekly Schedule Deleted Successfully", HttpStatus.OK);
	}
}