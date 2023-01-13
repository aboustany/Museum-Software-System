package ca.mcgill.ecse321.museumsystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VisitorIntegrationTests {
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		visitorRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndGetVisitor() {
		int id = testCreateVisitor();
		testGetVisitor(id);
	}
	
	private int testCreateVisitor() {
		Visitor visitor = new Visitor();
		visitor.setUserName("Mehdi");
		visitor.setPassword("Ammor");
		ResponseEntity<Visitor> response = client.postForEntity("/visitor", visitor, Visitor.class);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Mehdi", response.getBody().getUserName(), "Response has correct name");
		assertNotNull(response.getBody().getAccountID(), "Response has valid ID");
		return response.getBody().getAccountID();
	}
	
	private void testGetVisitor(int id) {
		ResponseEntity<Visitor> response = client.getForEntity("/visitor/" + id, Visitor.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Mehdi", response.getBody().getUserName(), "Response has correct name");
		assertTrue(response.getBody().getAccountID() == id, "Response has correct ID");
	}

}