package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;

public interface EmployeeWeeklyScheduleRepository extends CrudRepository<EmployeeWeeklySchedule, String>{
	EmployeeWeeklySchedule findEmployeeWeeklyScheduleByScheduleID(int scheduleID);
}
