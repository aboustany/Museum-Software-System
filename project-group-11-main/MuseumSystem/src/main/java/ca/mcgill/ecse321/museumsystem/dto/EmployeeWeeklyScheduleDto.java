package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;

/**
 * 
 * @author Lisa Morcellet
 * 
 * DTO for EmployeeWeeklySchedule class.
 * 
 * @param int id
 * @param string startDay
 * @param string endDay
 * @param Employee employee
 */

public class EmployeeWeeklyScheduleDto {
	
	  private int scheduleID;
	  private String startDay;
	  private String endDay;
	  private EmployeeDto employee;
	  
	  public EmployeeWeeklyScheduleDto() {  
	  }
	  
	  public EmployeeWeeklyScheduleDto(EmployeeWeeklySchedule employeeWeeklySchedule) {  
		  if (employeeWeeklySchedule != null) {
			  this.scheduleID = employeeWeeklySchedule.getScheduleID();
			  this.startDay = employeeWeeklySchedule.getStartDay();
			  this.endDay = employeeWeeklySchedule.getEndDay();
		  }
	  }
	  
	  public int getScheduleID() {
		  return this.scheduleID;
	  }
	  
	  public String getStartDay() {
		  return this.startDay;
	  }
	  
	  public String getEndDay() {
		  return this.endDay;
	  }
}