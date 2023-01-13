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

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;

/**
 * 
 * @author Anthony Boustany
 * 
 * Artwork Integration Tests.
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArtworkIntegrationTest {
	
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private ArtworkRepository artworkPassRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		artworkPassRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndGetArtwork() {
		int id = testCreateArtwork();
		testGetArtwork(id);
	}
	
	
	private int testCreateArtwork() {
		Artwork artwork = new Artwork();
		artwork.setName("Elsa");
		ResponseEntity<Artwork> response = client.postForEntity("/artwork", artwork, Artwork.class);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Elsa", response.getBody().getName(), "Response has correct name");
		assertNotNull(response.getBody().getArtworkID(), "Response has valid ID");
		return response.getBody().getArtworkID();
	}
	
	private void testGetArtwork(int id) {
		ResponseEntity<Artwork> response = client.getForEntity("/artwork/" + id, Artwork.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals("Elsa", response.getBody().getName(), "Response has correct name");
		assertTrue(response.getBody().getArtworkID() == id, "Response has correct ID");
	}

}