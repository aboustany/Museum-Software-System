

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
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;

/**
* The ArtworkRepositoryTests tests the Artwork class persistence layer.
*
* @author  Lisa Morcellet
* @version 2.0
*/

@SpringBootTest
public class ArtworkRepositoryTests {
	//get instance of ArtworkRepository
	@Autowired
	private ArtworkRepository artworkRepository;
	
	//get instance of OwnerRepository
	@Autowired
	private OwnerRepository ownerRepository;
	
	//get instance of MuseumSystemRepository
	@Autowired
	private MuseumSystemRepository museumSystemRepository;
	
	//get instance of RoomRepository
	@Autowired
	private RoomRepository roomRepository;
	
	//clear all relevant tables
	@AfterEach
	public void clearDatabase() {
		artworkRepository.deleteAll();
		roomRepository.deleteAll();
		ownerRepository.deleteAll();
		roomRepository.deleteAll();
	}
	
	
	//test method
	@Test
	public void testPersisAndLoadArtwork() {
		
		//create an owner
		Owner owner = new Owner();
		owner.setUserName("sami");
		//owner.setAccountID(1543623);
		owner.setPassword(":O");
		owner = ownerRepository.save(owner);
		
		//create a museun
		MuseumSystem museum = new MuseumSystem();
		museum.setOpeningHour("13:00");
		museum.setClosingHour("10:00");
		museum.setVisitorFee(4);
		//museum.setOwner(owner);
		//museumSystemRepository.save(museum);

		//create a small room
		Room room = new Room();
		//room.setRoomID(1);
		room.setCapacity(200);
		room.setNumberOfArtwork(1);
		room = roomRepository.save(room);
		
		//create artwork
		Artwork artPiece = new Artwork();
		boolean onDisplay = true;
		artPiece.setOnDisplay(onDisplay);
		//artPiece.setArtworkID(5655362);
		artPiece.setRoom(room);
		
		// Save object
		artPiece = artworkRepository.save(artPiece);
		int id = artPiece.getArtworkID();
		
		// Read object from database
		artPiece = artworkRepository.findArtworkByArtworkID(id);
		
		// Assert that object has correct attributes
		assertNotNull(artPiece); //verify object persist
		assertEquals(id, artPiece.getArtworkID()); //verify ID
		assertEquals(onDisplay, artPiece.getOnDisplay()); //verify display status
	}
}
