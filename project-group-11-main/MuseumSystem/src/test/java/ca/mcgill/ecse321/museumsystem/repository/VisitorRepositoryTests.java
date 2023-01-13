package ca.mcgill.ecse321.museumsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.museumsystem.model.*;

@SpringBootTest
public class VisitorRepositoryTests {
	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private MuseumSystemRepository museumSystemRepository;

	@Autowired
	private OwnerRepository ownerRepository;	
	
	
	/**
	 * 
	 * 
	 * @author ElsaChelala
	 * */
	
	@AfterEach
	public void clearDatabase() {
		visitorRepository.deleteAll();
		ownerRepository.deleteAll();
		museumSystemRepository.deleteAll();
	}
	
	
	/*
	 * testPersistAndLoadVisitor is a method used to create an visitor, store it in our database 
	 * and check whether it has been correctly stores.
	 * 
	 * @author ElsaChelala
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadVisitor() {
		// Create Museum Object 
		MuseumSystem museum = new MuseumSystem();
		
		//Create Owner and save it to the database
		Owner owner = new Owner();
		String userName = "SamiF";
		String password = "sucks";
		//int ownerId = 1;
		owner.setUserName(userName);
		owner.setPassword(password);
		//owner.setAccountID(ownerId);
		owner = ownerRepository.save(owner);
		
//		museum.setOwner(owner);
//		museumSystemRepository.save(museum);
//		
		//Create visitor and save it to the database
		Visitor visitor = new Visitor();
		String visitorUsername = "KarimK";
		String visitorPassword = "Goat";
		//int visitorId = 2;
		visitor.setUserName(visitorUsername);
		visitor.setPassword(visitorPassword);
		//visitor.setAccountID(2);
		visitor = visitorRepository.save(visitor);
		
		Visitor test = visitorRepository.findVisitorByAccountID(visitor.getAccountID());
		
		assertEquals(visitor.getAccountID(), test.getAccountID());
		assertEquals(visitorUsername, test.getUserName());
		assertEquals(visitorPassword, test.getPassword());
		
	}

}
