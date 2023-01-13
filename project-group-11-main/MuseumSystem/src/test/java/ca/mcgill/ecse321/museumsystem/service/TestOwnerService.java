package ca.mcgill.ecse321.museumsystem.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;


/**
*
* 
* @Author Mehdi Ammor 
* */

//Unit Test For Owner Service.

@ExtendWith(MockitoExtension.class)
public class TestOwnerService {
	@Mock 
	OwnerRepository ownerRepository;
	@Mock 
	EmployeeRepository employeeRepository;
	@Mock
	RoomRepository roomRepository;
	@Mock
	ArtworkRepository artWorkRepository;
	@InjectMocks
	private OwnerService ownerService;
	
	@Mock
	private EmployeeService employeeService;
	private static final String PERSON_KEY = "TestOwner";
	private Owner mehdi;
	private Employee karim;
	
	@BeforeEach
	public void createObjects() {
	//Create Owner 
 	mehdi = new Owner();
	String userName = "Mehdi";
	String password = "123456789";
	//Owner mehdi = new Owner();
	mehdi.setUserName(userName);
	mehdi.setPassword(password);
	mehdi.setAccountID(0);
	//Create Employee
	karim = new Employee();
	karim.setUserName("Karim");
	karim.setPassword("Kanafani");
	karim.setAccountID(1);
	employeeRepository.save(karim);
	    }
	@AfterEach
    public void deleteObjects() {
        mehdi.delete();
    }

	//Testing Creating Owner
	@Test
	public void testCreateOwner() {
	when(ownerRepository.save(any(Owner.class))).thenAnswer((InvocationOnMock invocation)->mehdi);
	Owner testMehdi = ownerService.createOwner(mehdi);
	assertEquals(testMehdi.getUserName(), "Mehdi");
	assertEquals(testMehdi.getPassword(), "123456789");
	assertEquals(testMehdi.getAccountID(), 0);
	verify(ownerRepository, times(1)).save(any(Owner.class)); 
	}
	
	
	//Testing Creating Owner but Null
	@Test
	public void testCreateOwnerNull() {
	Owner noOwner = null;
	//when(ownerRepository.save(any(Owner.class))).thenAnswer((InvocationOnMock invocation)->invocation.getArgument(0));
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.createOwner(noOwner));
	assertEquals("Owner cannot be null", exception.getMessage());
	assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
	}

 //Testing Getting Owner	
   @Test
    public void testGetOwnerByID() {   
	when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
	Owner Testowner = ownerService.getOwnerbyID(0); 
	assertNotNull(Testowner);
	assertEquals(mehdi.getAccountID(), Testowner.getAccountID());
	assertEquals(mehdi.getUserName(), Testowner.getUserName());
	verify(ownerRepository, times(1)).findOwnerByAccountID(0); 
    }
   @Test
	public void testGetOwnerByInvalidId() {
	final int invalidId = 99;
	when(ownerRepository.findOwnerByAccountID(invalidId)).thenAnswer((InvocationOnMock invocation) -> null);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.getOwnerbyID(invalidId));
	assertEquals("Owner doesn't exist", exception.getMessage());
	assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
   	}
   
   //Testing with updating the password with less than 8 characters
   @Test
   public void testInvalidUpdateOwnerPassword() {
	when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerPassword(0, "123456789", "3214"));
	assertEquals("Invalid Password. The password must have a minimum of 8 characters", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
 //Testing with updating a new password with invalid password
   @Test
   public void testWrongPassword() {
	when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerPassword(0, "wrong", "3214"));
	assertEquals("Wrong Password.", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
 //Testing with updating the password with the same existing password 
   @Test
   public void testUpdateWithSamePassword() {
	when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerPassword(0, "123456789", "123456789"));
	assertEquals("The new password cannot be the same as the old password", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
   //Testing with updating the password with the same existing password 
   @Test
   public void testUpdatePasswordWithInvalidOwnerID() {
	when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> null);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerPassword(1324, "123456789", "123456789321"));
	assertEquals("Owner doesn't exist", exception.getMessage());
	assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
   
   //testing updating Username but with invalid owner ID
   @Test
   public void testUpdateUsernameWithInvalidOwnerID() {
    when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> null);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerUsername(1324, "123456789", "1234567893214321"));
	assertEquals("Owner doesn't exist", exception.getMessage());
	assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
   
   //Test updating Username with wrong Password
   @Test
   public void testUpdateUsernameWithWrongPassword() {
    when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerUsername(mehdi.getAccountID(), "blablablaba", "1234567893214321"));
	assertEquals("Wrong Password.", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
   //Test successful updating a new Valid Password
   @Test
   public void testUpdateValidPassword() {
	String newPassword = "newPassword1234";
    when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
	Owner Testowner = ownerService.UpdateOwnerPassword(0,"123456789", newPassword); 
	assertNotNull(Testowner);
	assertEquals(mehdi.getPassword(), newPassword);
	verify(ownerRepository, times(1)).findOwnerByAccountID(0); 
	}
   
   //Test with valid Userename
   @Test
   public void testUpdateValidUsername() {
	String newUsername = "newUsername@gmail.com";
    when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
	Owner Testowner = ownerService.UpdateOwnerUsername(0,"123456789", newUsername); 
	assertNotNull(Testowner);
	assertEquals(mehdi.getUserName(), newUsername);
	verify(ownerRepository, times(1)).findOwnerByAccountID(0); 
	}
   //Test with invalid Username ( without '@')
   @Test
   public void testUpdateInvalidUsername() {
	String newUsername = "newUsernamegmail.com";
    when(ownerRepository.findOwnerByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
    MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> ownerService.UpdateOwnerUsername(0, "123456789", newUsername));
	assertEquals("Invalid Username. The email address must contain a @ and a '.' , Please try again.", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
}
