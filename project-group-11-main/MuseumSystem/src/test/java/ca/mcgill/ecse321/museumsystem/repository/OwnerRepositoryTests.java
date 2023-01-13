package ca.mcgill.ecse321.museumsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.Visitor;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OwnerRepositoryTests {

	@Autowired
	private OwnerRepository accountRepository;

	@Autowired
	private MuseumSystemRepository museumSystemRepository;
	
	/**
	 *
	 * 
	 * @author Sami Ferneini
	 * */
	
	@AfterEach
	public void clearDatabase() {
		accountRepository.deleteAll();
		museumSystemRepository.deleteAll();
	}
	/*
	 * testPersistAndLoadOwner is a method used to create an owner, store it in our database 
	 * and check whether it has been correctly stored.
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadOwner() {
		// Create Object 	
		//int accountID = 12345;
		Owner owner = new Owner(); // instantiating owner
		String OwneruserName = "Hello";
		String Ownerpassword = "Bye";
		//owner.setAccountID(accountID);
		owner.setUserName(OwneruserName);
		owner.setPassword(Ownerpassword);
		
		owner = accountRepository.save(owner);
		
		// read owner from database
		Owner test = accountRepository.findOwnerByAccountID(owner.getAccountID());
		
		assertNotNull(test); // verifying owner persists
		assertEquals(owner.getAccountID(), test.getAccountID()); // verifying ID
		assertEquals(OwneruserName, test.getUserName());// verifying name
		assertEquals(Ownerpassword, test.getPassword());//  verifying password
		
	}
	
}
