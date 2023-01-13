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

import ca.mcgill.ecse321.museumsystem.model.EntryPass;
import ca.mcgill.ecse321.museumsystem.model.EntryPassRepository;

/**
 * 
 * @author Anthony Boustany
 * 
 * EntryPass Integration tests.
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EntryPassIntegrationTest {
	
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private EntryPassRepository entryPassRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		entryPassRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndGetEntryPass() {
		int id = testCreateEntryPass();
		testGetEntryPass(id);
	}
	
	private int testCreateEntryPass() {
		EntryPass entryPass = new EntryPass();
		entryPass.setDate("29/11/2001");
		ResponseEntity<EntryPass> response = client.postForEntity("/entrypass", entryPass, EntryPass.class);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("29/11/2001", response.getBody().getDate(), "Response has correct name");
		assertNotNull(response.getBody().getPassID(), "Response has valid ID");
		return response.getBody().getPassID();
	}
	
	private void testGetEntryPass(int id) {
		ResponseEntity<EntryPass> response = client.getForEntity("/entrypass/" + id, EntryPass.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(10, response.getBody().getPrice(), "Response has correct name");
		assertTrue(response.getBody().getPassID() == id, "Response has correct ID");
	}

}