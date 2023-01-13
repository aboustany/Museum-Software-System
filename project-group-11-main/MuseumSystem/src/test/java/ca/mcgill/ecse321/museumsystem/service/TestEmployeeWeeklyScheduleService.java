package ca.mcgill.ecse321.museumsystem.service;

import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklyScheduleRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;

/**
 * This class tests the service methods for the EmployeeWeeklySchedule class.
 * This tests creating and getting employee schedules.
 * @author Karim
 * 
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestEmployeeWeeklyScheduleService {
	
	
	@Mock
	EmployeeWeeklyScheduleRepository employeeWeeklyScheduleRepository;
	
	@InjectMocks
	private EmployeeWeeklyScheduleService employeeWeeklyScheduleService;
	
	private static final String PERSON_KEY = "TestEmployee";
	private static final int EMP_ID = 12;
	private static final int SCH_ID = 12;
	private static final int RM_ID = 12;
	
	
	@BeforeEach
	public void setMockOuput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};

		lenient().when(employeeWeeklyScheduleRepository.save(any(EmployeeWeeklySchedule.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SCH_ID)) {
				Employee tmpEmployee = new Employee();
				tmpEmployee.setUserName(PERSON_KEY);
				tmpEmployee.setAccountID(EMP_ID);
				Owner tmpOwner = new Owner();
				tmpOwner.setUserName(PERSON_KEY);
				tmpOwner.setAccountID(13);
				EmployeeWeeklySchedule tmpEmployeeWeeklySchedule = new EmployeeWeeklySchedule(tmpOwner,tmpEmployee);
				tmpEmployeeWeeklySchedule.setScheduleID(SCH_ID);
				return tmpEmployeeWeeklySchedule;
			} else {
				return null;
			}
		});
	}
	
	@Test
	public void testGetExistingEmployeeWeeklyScheduleById() {
		Employee tmpEmployee = new Employee();
		tmpEmployee.setUserName(PERSON_KEY);
		tmpEmployee.setAccountID(EMP_ID);
		Owner tmpOwner = new Owner();
		tmpOwner.setUserName(PERSON_KEY);
		tmpOwner.setAccountID(13);
		EmployeeWeeklySchedule weeklySchedule = new EmployeeWeeklySchedule(tmpOwner,tmpEmployee);
		weeklySchedule.setScheduleID(12);
		weeklySchedule = employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(weeklySchedule.getScheduleID());
		try {
			EmployeeWeeklySchedule schedule = employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(weeklySchedule.getScheduleID());
			assertEquals(weeklySchedule.getScheduleID(),schedule.getScheduleID());
		}catch(Exception e) {
			System.out.println("Employee Weekly Schedule not found");
			fail();
		}
	}
	
	@Test
	public void testGetNonExistingEmployeeWeeklyScheduleById() {
		int id = 1;	
		EmployeeWeeklySchedule weeklySchedule = employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(id);
		assertNull(weeklySchedule);
	}
	
	@Test
	public void testCreateEmployeeWeeklySchedule() {
		EmployeeWeeklySchedule schedule = new EmployeeWeeklySchedule();
		try {
			schedule = employeeWeeklyScheduleService.createEmployeeWeeklySchedule(schedule);
			assertNotNull(schedule.getScheduleID());
		} catch(IllegalArgumentException e){
			fail();
		}
	}
	
	@Test
	public void testCreateEmployeeWeeklyScheduleNull() {
		EmployeeWeeklySchedule schedule = null;
		schedule = employeeWeeklyScheduleService.createEmployeeWeeklySchedule(schedule);
		assertNull(schedule);
	}
}
