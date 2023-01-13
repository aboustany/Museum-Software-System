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
 * This class tests the service methods for the Employee class.
 * This tests creating and getting employees.
 * @author Karim
 * 
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
	
	@Mock 
	EmployeeRepository employeeRepository;
	
	@Mock
	EmployeeWeeklyScheduleRepository employeeWeeklyScheduleRepository;
	
	@Mock
	RoomRepository roomRepository;
	
	@Mock
	ArtworkRepository artworkRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private static final String PERSON_KEY = "TestEmployee";
	private static final int EMP_ID = 12;
	private static final int SCH_ID = 12;
	private static final int RM_ID = 12;
	
	
	@BeforeEach
	public void setMockOuput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(employeeRepository.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(roomRepository.save(any(Room.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(employeeWeeklyScheduleRepository.save(any(EmployeeWeeklySchedule.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(artworkRepository.save(any(Artwork.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(employeeRepository.findEmployeeByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMP_ID)) {
				Employee tmpEmployee = new Employee();
				tmpEmployee.setUserName(PERSON_KEY);
				tmpEmployee.setAccountID(invocation.getArgument(0));
				return tmpEmployee;
			} else {
				return null;
			}
		});
		
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
		
		lenient().when(roomRepository.findRoomByRoomID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(RM_ID)) {
				Room tmpRoom = new Room();
				tmpRoom.setCapacity(200);
				return tmpRoom;
			} else {
				return null;
			}
		});
	}
	
	@Test
	public void testCreateEmployee() {
		
		String userName = "KK";
		String password = "TestPass";
		Employee karim = new Employee();
		karim.setUserName(userName);
		karim.setPassword(password);
		try {
			karim = employeeService.createEmployee(karim);
			assertNotNull(karim.getAccountID());
		} catch(IllegalArgumentException e){
			fail();
		}
		
	}
	
	@Test
	public void testCreateEmployeeNull() {
		String username = null;
		String pass = null;
		Employee employee = null;
		String error = null;
		try {
			employee = employeeService.createEmployee(employee);
			assertNull(employee);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
	}
	
	@Test
	public void testGetNonExistingEmployee() {
		int id = 36;
		Employee employee = employeeService.getEmployeeById(id);
		assertNull(employee);
	}
	
	@Test
	public void testGetExistingEmployee() {
		Employee karim = new Employee();
		karim = employeeService.createEmployee(karim);
		karim.setAccountID(12);
		try {
			Employee employee = employeeService.getEmployeeById(karim.getAccountID());
			assertEquals(karim.getAccountID(),employee.getAccountID());
		}catch(Exception e) {
			System.out.println("Employee not found");
			fail();
		}
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
	public void testSetArtworkToFullRoom() {
		
	}
	
	@Test
	public void testSetArtworkToCorrectRoom() {
		
	}
	
	@Test
	public void testSetArtworkToSameRoom() {
		
	}
	
	@Test
	public void testSetArtworkLoanNewStatus() {
		
	}
	
	@Test
	public void testSetArtworkLoanSameStatus() {
		
	}

}
