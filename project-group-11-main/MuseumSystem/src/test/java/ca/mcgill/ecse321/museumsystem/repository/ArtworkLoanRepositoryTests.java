package ca.mcgill.ecse321.museumsystem.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoanRepository;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.LoanableArtwork;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;

/**
 * 
 * @author Karim Kanafani
 *
 */
@SpringBootTest
public class ArtworkLoanRepositoryTests {
	@Autowired
	private ArtworkLoanRepository artworkLoanrepository;
	
	@Autowired
	private MuseumSystemRepository museumSystemRepository; 
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@Autowired
	private ArtworkRepository artworkRepository;
	
	@Autowired
	private RoomRepository roomRepository; 
	
	
	@AfterEach
	public void clearDatabase() { // Clear the tables
		artworkLoanrepository.deleteAll();
		artworkRepository.deleteAll();
		roomRepository.deleteAll();
		visitorRepository.deleteAll();
	}
	
	@Test
	public void testPersisAndLoadArtworkLoan() { // Testing
		//create artwork loan
		
		Visitor visitor = new Visitor(); // Instantiate visitor
		String visitorUsername = "KarimK";
		String visitorPassword = "Goat";
		//int visitorId = 2;
		visitor.setUserName(visitorUsername);
		visitor.setPassword(visitorPassword);
		//visitor.setAccountID(visitorId);
		visitor = visitorRepository.save(visitor);
		
		Room room = new Room(); // Instantiate room
		//room.setRoomID(1);
		room.setCapacity(200);
		room.setNumberOfArtwork(1);
		room = roomRepository.save(room);
		
	
		
		LoanableArtwork artwork = new LoanableArtwork(); // Instantiate artwork
		Boolean isAvailable = false;
		int rentalFee = 100;
		artwork.setIsAvailable(isAvailable);
		artwork.setRentalFee(rentalFee);
		//artwork.setArtworkID(4673837);
		artwork.setRoom(room);
		artwork = artworkRepository.save(artwork);
		
		ArtworkLoan artPieceLoan = new ArtworkLoan(); // Instantiate loan
		boolean isApproved = true;
		String startDate = "2022/10/01";
		String endDate = "2022/10/31";
		artPieceLoan.setVisitor(visitor);
		artPieceLoan.setLoanableArtwork(artwork);
		artPieceLoan.setIsApproved(isApproved); 
		artPieceLoan.setEndDay(endDate);
		artPieceLoan.setStartDay(startDate);
		//artPieceLoan.setLoanID(3);
		artPieceLoan = artworkLoanrepository.save(artPieceLoan);
		
		// Read object from database
		ArtworkLoan test = artworkLoanrepository.findArtworkLoanByLoanID(artPieceLoan.getLoanID());  // Get from database
		
		// Assert that object has correct attributes
		assertNotNull(test); //verify object persist
		assertEquals(artPieceLoan.getLoanID(), test.getLoanID()); //verify ID
		assertEquals(isApproved, test.getIsApproved()); //verify approval status
		assertEquals(startDate, test.getStartDay());
		assertEquals(endDate, test.getEndDay());
	}
}
