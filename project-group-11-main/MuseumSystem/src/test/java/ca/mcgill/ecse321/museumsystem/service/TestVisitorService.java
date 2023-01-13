package ca.mcgill.ecse321.museumsystem.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;


/**
*
* 
* @author Mehdi Ammor
* */

//Visitor Service Unit Test Implemented

@ExtendWith(MockitoExtension.class)
public class TestVisitorService {
	
	@Mock 
	VisitorRepository visitorRepository;

	@InjectMocks
	private VisitorService visitorService;
	
	private static final String PERSON_KEY = "TestVisitor";
	private Visitor mehdi;
	ArrayList<Visitor> allVisitors;
	private static final int VIS_ID = 5;
	
	
	@BeforeEach
	public void createObjects() {
		
	//Create Visitor 
	mehdi = new Visitor();
	String userName = "Mehdi";
	String password = "123456789";
	//Visitor mehdi = new Visitor();
	mehdi.setUserName(userName);
	mehdi.setPassword(password);
	mehdi.setAccountID(VIS_ID);
	    }
	
	//for testing "getting all visitors
	@BeforeEach
	public void createAllVisitors() {
    allVisitors = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        Visitor visitor = new Visitor();
        visitor.setAccountID(i);
        visitor.setPassword("Hello1234" +String.valueOf(i));
        visitor.setUserName("Hello@gmail.com1234" +String.valueOf(i));
        allVisitors.add(visitor);
    }
	}
	//Delete visitor 
	@AfterEach
	public void deleteObjects() {
    mehdi.delete();
    allVisitors.clear();
	}
	
	//Testing Creating Visitor
	@Test
	public void testCreateValidVisitor() {
	when(visitorRepository.save(any(Visitor.class))).thenAnswer((InvocationOnMock invocation)->mehdi);
	Visitor testMehdi = visitorService.createVisitor(mehdi);
	assertEquals(testMehdi.getUserName(), "Mehdi");
	assertEquals(testMehdi.getPassword(), "123456789");
	assertEquals(testMehdi.getAccountID(), VIS_ID);
	verify(visitorRepository, times(1)).save(any(Visitor.class)); 
	}
	
	
	//Testing Creating Visitor but Null
	@Test
	public void testCreateVisitorNull() {
	Visitor noVisitor = null;
	//when(visitorRepository.save(any(Visitor.class))).thenAnswer((InvocationOnMock invocation)->invocation.getArgument(0));
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.createVisitor(noVisitor));
	assertEquals("Visitor cannot be null", exception.getMessage());
	assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
	}
	
	//Testing getting nonExistentVisitor
	@Test
	public void testGetNonExistingVisitor() {
	int id = 99;
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.getVisitorByID(id));
	assertEquals("Visitor doesn't exist", exception.getMessage());
	assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
	
	//Testing getting a visitor that exists in the Database
	@Test
	public void testGetExistingVisitor() {
	when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	Visitor testMehdi = visitorService.getVisitorByID(mehdi.getAccountID());
	assertEquals(testMehdi.getUserName(), mehdi.getUserName());
	assertEquals(testMehdi.getPassword(), mehdi.getPassword());
	assertEquals(testMehdi.getAccountID(), mehdi.getAccountID());
	}
	
	//Get all visitors 
	@Test
	public void testGetAllExistingVisitors() {
	when(visitorRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> allVisitors);
	ArrayList<Visitor> visitors = visitorService.getAllVisitors();
	assertEquals(visitors, allVisitors);
	}
	
	
 //Testing with updating the password with less than 8 characters
   @Test
   public void testInvalidUpdateVisitorPassword() {
	when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorPassword(0, "123456789", "3214"));
	assertEquals("Invalid Password. The password must have a minimum of 8 characters", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
 //Testing with updating a new password with invalid password
   @Test
   public void testWrongPassword() {
	when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorPassword(0, "wrong", "3214"));
	assertEquals("Wrong Password.", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
 //Testing with updating the password with the same existing password 
   @Test
   public void testUpdateWithSamePassword() {
	when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorPassword(0, "123456789", "123456789"));
	assertEquals("The new password cannot be the same as the old password", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
   //Testing with updating the password with the same existing password 
   @Test
   public void testUpdatePasswordWithInvalidVisitorID() {
	when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> null);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorPassword(1324, "123456789", "123456789321"));
	assertEquals("Visitor doesn't exist", exception.getMessage());
	assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
   
   //testing updating Username but with invalid Visitor ID
   @Test
   public void testUpdateUsernameWithInvalidVisitorID() {
    when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> null);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorUsername(1324, "123456789", "1234567893214321"));
	assertEquals("Visitor doesn't exist", exception.getMessage());
	assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
   
   //Test updating Username with wrong Password
   @Test
   public void testUpdateUsernameWithWrongPassword() {
    when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation) -> mehdi);
	MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorUsername(mehdi.getAccountID(), "blablablaba", "1234567893214321"));
	assertEquals("Wrong Password.", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
   
   //Test successful updating a new Valid Password
   @Test
   public void testUpdateValidPassword() {
	String newPassword = "newPassword1234";
    when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
	Visitor TestVisitor = visitorService.UpdateVisitorPassword(0,"123456789", newPassword); 
	assertNotNull(TestVisitor);
	assertEquals(mehdi.getPassword(), newPassword);
	verify(visitorRepository, times(1)).findVisitorByAccountID(0); 
	}
   
   //Test with valid Userename
   @Test
   public void testUpdateValidUsername() {
	String newUsername = "newUsername@gmail.com";
    when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
	Visitor TestVisitor = visitorService.UpdateVisitorUsername(0,"123456789", newUsername); 
	assertNotNull(TestVisitor);
	assertEquals(mehdi.getUserName(), newUsername);
	verify(visitorRepository, times(1)).findVisitorByAccountID(0); 
	}
   //Test with invalid Username ( without '@')
   @Test
   public void testUpdateInvalidUsername() {
	String newUsername = "newUsernamegmail.com";
    when(visitorRepository.findVisitorByAccountID(anyInt())).thenAnswer((InvocationOnMock invocation)->mehdi);
    MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> visitorService.UpdateVisitorUsername(0, "123456789", newUsername));
	assertEquals("Invalid Username. The email address must contain a @ and a '.' , Please try again.", exception.getMessage());
	assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
	}
}
