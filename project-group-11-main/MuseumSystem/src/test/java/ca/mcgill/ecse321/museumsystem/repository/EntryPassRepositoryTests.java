/**
 * 
 * @author Anthony Boustany
 *
 */
package ca.mcgill.ecse321.museumsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.EntryPass;
import ca.mcgill.ecse321.museumsystem.model.EntryPassRepository;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EntryPassRepositoryTests {
	@Autowired
	private EntryPassRepository entryPassRepository ;

	@Autowired
	private VisitorRepository visitorRepository ;
	
	@AfterEach
	public void clearDatabase() {
		// Delete the registrations first to avoid violating not-null constraint
		entryPassRepository.deleteAll();
		visitorRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadEntryPass() {
		
		// Create entryPass and visitor objects
		EntryPass entryPass = new EntryPass();
		Visitor visitor = new Visitor();
		String visitorUsername = "KarimK";
		String visitorPassword = "Goat";
		//int visitorId = 2;
		visitor.setUserName(visitorUsername);
		visitor.setPassword(visitorPassword);
		//visitor.setAccountID(2);
		entryPass.setPrice(5);
		entryPass.setDate("2022/10/01");
		entryPass.setVisitor(visitor);	
		
		// Save Objects
		visitorRepository.save(visitor);
		entryPassRepository.save(entryPass);
		
		// Read entryPass from database
		EntryPass test = entryPassRepository.findEntryPassByPassID(entryPass.getPassID());
		
		// Assert that object has correct attributes
		assertNotNull(test);
		assertEquals(entryPass.getPassID(), test.getPassID());
		assertEquals(entryPass.getPrice(), test.getPrice());
		assertEquals(entryPass.getDate(), test.getDate());
	}
}
