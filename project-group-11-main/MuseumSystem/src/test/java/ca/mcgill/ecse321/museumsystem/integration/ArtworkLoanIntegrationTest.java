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

import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoanRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArtworkLoanIntegrationTest {
	
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private ArtworkLoanRepository artworkLoanRepository;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		artworkLoanRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndGetArtworkLoan() {
		int id = testCreateArtworkLoan();
		testArtworkLoan(id);
	}
	
	private int testCreateArtworkLoan() {
		ArtworkLoan loan = new ArtworkLoan();
		loan.setStartDay("01/01/2000");
		loan.setEndDay("08/01/2000");
		ResponseEntity<ArtworkLoan> response = client.postForEntity("/artwork_loan", loan, ArtworkLoan.class);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertNotNull(response.getBody().getLoanID(), "Response has valid ID");
		return response.getBody().getLoanID();
	}
	
	private void testArtworkLoan(int id) {
		ResponseEntity<ArtworkLoan> response = client.getForEntity("/artwork_loan/" + id, ArtworkLoan.class);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertTrue(response.getBody().getLoanID() == id, "Response has correct ID");
	}
}
