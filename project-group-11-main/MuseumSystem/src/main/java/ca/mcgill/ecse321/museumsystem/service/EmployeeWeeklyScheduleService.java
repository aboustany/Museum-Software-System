package ca.mcgill.ecse321.museumsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklyScheduleRepository;
import ca.mcgill.ecse321.museumsystem.model.LoanableArtwork;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;


/**
 * 
 * @author Karim
 * 
 * Service for Employee class.
 *
 */
@Service
public class EmployeeWeeklyScheduleService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeWeeklyScheduleRepository employeeWeeklyScheduleRepository;
	
	@Transactional
	public EmployeeWeeklySchedule getEmployeeWeeklyScheduleById(int id) {
		try {
			EmployeeWeeklySchedule employeeWeeklySchedule = employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(id);
			return employeeWeeklySchedule;
		}catch(Exception e) {
			System.out.println("Employee Weekly Schedule not found.");
		}return null;
	}
	
	@Transactional
	public EmployeeWeeklySchedule createEmployeeWeeklySchedule(EmployeeWeeklySchedule schedule) {
		schedule = employeeWeeklyScheduleRepository.save(schedule);
		return schedule;
	}
	
	@Transactional
	public void deleteEmployeeWeeklySchedule(EmployeeWeeklySchedule schedule) {
		employeeWeeklyScheduleRepository.delete(schedule);
	}
}
