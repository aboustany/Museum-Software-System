package ca.mcgill.ecse321.museumsystem.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;



/**
*
* 
* @author Mehdi Ammor
* */


//Museum System Service Tests Implemented (with login)

@ExtendWith(MockitoExtension.class)
public class TestMuseumSystemService {
	
	@Mock
	MuseumSystemRepository museumSystemRepository;
	
	@InjectMocks
	private MuseumSystemService museumSystemService;
	
	private static final String PERSON_KEY = "TestMuseumSystem";
	
	private MuseumSystem museum;
	
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
	
	 @BeforeEach
    public void createObjects() {
	 //Create museum 
	 	museum = new MuseumSystem();
	 	museum.setOpeningHour("09:00");
	 	museum.setClosingHour("17:00");
	 	museum.setVisitorFee(20);
    }
	
	@Test
	public void testCreateMuseum() {
		when(museumSystemRepository.save(any(MuseumSystem.class))).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystem museumSystem = museumSystemService.createMuseumSystem(museum);
		assertEquals(museumSystem.getMuseumID(), 0);
		assertEquals(museumSystem.getClosingHour(), "17:00");
		assertEquals(museumSystem.getOpeningHour(), "09:00");
		verify(museumSystemRepository, times(1)).save(any(MuseumSystem.class)); 
	}
	
 //Testing Getting MuseumSystem with Valid ID	
   @Test
    public void testGetMuseumSystemByID() { 
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystem museumSystemTest = museumSystemService.getMuseumSystemByID(0);
		assertEquals(museum.getMuseumID(), museumSystemTest.getMuseumID());
		assertEquals(museum.getClosingHour(), museumSystemTest.getClosingHour());
		assertEquals(museum.getOpeningHour(), museumSystemTest.getOpeningHour());
		verify(museumSystemRepository, times(1)).findMuseumSystemByMuseumID(0);  
    }
   
   //Testing Getting MuseumSystem with Invalid ID	
   @Test
	public void testGetMuseumSystemByInvalidId() {
		final int invalidId = 99;
		when(museumSystemRepository.findMuseumSystemByMuseumID(invalidId)).thenAnswer((InvocationOnMock invocation)->null);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.getMuseumSystemByID(invalidId));
		assertEquals("Museum doesn't exist", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
   
   
   //Test with Invalid Opening Hours Inputs
   @Test
   public void testSetInvalidOpeningHours() { 
	   final String invalidOpeningHours = "20";
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetOpeningHours(museum.getMuseumID(),invalidOpeningHours));
		assertEquals("Invalid OpeningHours. Try again with a format 'hh:mm' ", exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
   }
   
   //Testing with valid Opening Hours Inputs
   @Test
   public void testSetValidOpeningHours() { 
    String ValidOpeningHours = "08:30";
	when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
	MuseumSystem museumSystemTest = museumSystemService.SetOpeningHours(museum.getMuseumID(), ValidOpeningHours);
	assertEquals(museumSystemTest.getOpeningHour(), ValidOpeningHours);
	verify(museumSystemRepository, times(1)).findMuseumSystemByMuseumID(0);  
   }
   
 //Testing with Invalid museum ID
   @Test
   public void testSetOpeningHoursWithInvalidMuseumID() { 
	   String ValidOpeningHours = "08:30";
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->null);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetClosingHours(museum.getMuseumID(),ValidOpeningHours));
		assertEquals("Museum doesn't exist", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
   }
   
   //Testing with Invalid museum ID
   @Test
   public void testSetClosingHoursWithInvalidMuseumID() { 
	    String ValidOpeningHours = "12:00";
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->null);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetOpeningHours(museum.getMuseumID(),ValidOpeningHours));
		assertEquals("Museum doesn't exist", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
   }
   
   
   
   
   
   
   
   //Test Valid Closing Hours 
   @Test
   public void testSetValidClosingHours() { 
	    String ValidClosingHours = "16:00";
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystem museumSystemTest = museumSystemService.SetClosingHours(museum.getMuseumID(), ValidClosingHours);
		assertEquals(museumSystemTest.getClosingHour(), ValidClosingHours);
		verify(museumSystemRepository, times(1)).findMuseumSystemByMuseumID(0);  
   }
   //Wrong Opening Hours Inputs
   @Test
   public void testSetInvalidClosingHours() { 
	   final String invalidOpeningHours = "2020";
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetClosingHours(museum.getMuseumID(),invalidOpeningHours));
		assertEquals("Invalid ClosingHours. Try again with a format 'hh:mm' ", exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
   }
   //Testing Same Opening and Closing Hours
   @Test
   public void testSetInvalidHours() { 
	    String InvalidClosingHours = "09:00";
	    when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
	    MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetClosingHours(museum.getMuseumID(),InvalidClosingHours));
		assertEquals("OpeningHours and ClosingHours cannot be the same", exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
   }
   
 //Testing Same Cl and Closing Hours
   @Test
   public void testSetInvalidHours2() { 
	    String InvalidClosingHours = "17:00";
	    when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
	    MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetOpeningHours(museum.getMuseumID(),InvalidClosingHours));
		assertEquals("OpeningHours and ClosingHours cannot be the same", exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
   }
   
   
   //testing with setting visitor fee to be negative (invalid)
   @Test
   public void testSetInvalidVisitorFee() { 
	   	int SameVisitorFee = 20;
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetVisitorFee(museum.getMuseumID(),SameVisitorFee));
		assertEquals("VisitorFee already set to " + String.valueOf(SameVisitorFee), exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
   }
   //testing setting visitor fee with the same fee
   @Test
   public void testSetSameVisitorFee() { 
	   	int InvalidVisitorFee = -1;
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetVisitorFee(museum.getMuseumID(),InvalidVisitorFee));
		assertEquals("VisitorFee cannot be negative", exception.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
   }
   
   
   //testing setting visitor fee with the same fee
   @Test
   public void testSetVisitorFeeWithInvalidMuseumID() { 
	   	int ValidVisitorFee = 15;
	   	int InvalidMuseumID= 4231;
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->null);
		MuseumSystemException exception = assertThrows(MuseumSystemException.class, () -> museumSystemService.SetVisitorFee(InvalidMuseumID,ValidVisitorFee));
		assertEquals("Museum doesn't exist", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
   }
   
   //testing with setting visitor fee to be negative (invalid)
   @Test
   public void testSetValidVisitorFee() { 
	   	int ValidVisitorFee = 21;
		when(museumSystemRepository.findMuseumSystemByMuseumID(anyInt())).thenAnswer((InvocationOnMock invocation)->museum);
		MuseumSystem newMuseum = museumSystemService.SetVisitorFee(museum.getMuseumID(), ValidVisitorFee);
		assertEquals(newMuseum.getVisitorFee(), ValidVisitorFee);
		verify(museumSystemRepository, times(1)).findMuseumSystemByMuseumID(museum.getMuseumID()); 
   }
   

   
   
}
