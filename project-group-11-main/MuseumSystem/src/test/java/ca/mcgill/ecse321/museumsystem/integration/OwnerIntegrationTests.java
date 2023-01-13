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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OwnerIntegrationTests {
	
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		ownerRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndGetOwner() {
		int id = testCreateOwner();
		testGetOwner(id);
	}
	
	private int testCreateOwner() {
		Owner owner = new Owner();
		owner.setUserName("Mehdi");
		owner.setPassword("Ammor");
		ResponseEntity<Owner> response = client.postForEntity("/owner", owner, Owner.class);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Mehdi", response.getBody().getUserName(), "Response has correct name");
		assertNotNull(response.getBody().getAccountID(), "Response has valid ID");
		return response.getBody().getAccountID();
	}
	
	private void testGetOwner(int id) {
		ResponseEntity<Owner> response = client.getForEntity("/owner/" + id, Owner.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Mehdi", response.getBody().getUserName(), "Response has correct name");
		assertTrue(response.getBody().getAccountID() == id, "Response has correct ID");
	}

}