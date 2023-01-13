package ca.mcgill.ecse321.museumsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoomRepositoryTests {


	@Autowired
	private RoomRepository roomRepository;


	
	
	/**
	 * deleteEntries is a method used to clear our database
	 * 
	 * @author ElsaChelala
	 * */
	
	@AfterEach
	public void clearDatabase() {
		roomRepository.deleteAll();
	}
	
	
	/*
	 * testPersistAndLoadRoom() is a method used to create a room, store it in our database 
	 * and check whether it has been correctly stores.
	 * 
	 * @author ElsaChelala
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadRoom() {
		// Create Object 

		//roomID
		//capacity
		//numberOfArtwork
		Room room = new Room();
		//room.setRoomID(5321);
		room.setCapacity(200);
		room.setNumberOfArtwork(50);
		
		roomRepository.save(room);

		
		Room test = roomRepository.findRoomByRoomID(room.getRoomID());
		
		assertNotNull(test);
		assertEquals(room.getRoomID(), test.getRoomID());
		assertEquals(room.getCapacity(), test.getCapacity());
		assertEquals(room.getNumberOfArtwork(), test.getNumberOfArtwork());
		
		
	}
	
}
