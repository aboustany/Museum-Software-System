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

import ca.mcgill.ecse321.museumsystem.dto.RoomDto;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;
import ca.mcgill.ecse321.museumsystem.service.RoomService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RoomIntegrationTest {
	
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@BeforeEach
	public void clearDatabase() {
		roomRepository.deleteAll();
	}
	
	@Test
	public void testCreateAndGetRoom() {
		int id = testCreateRoom();
		testGetRoom(id);
	}
	
	private int testCreateRoom() {
		Room room1 = new Room();
		room1.setCapacity(200);
		room1.setNumberOfArtwork(0);
		room1.canSetRoomID = true;
		ResponseEntity<Room> response1 = client.postForEntity("/room", room1, Room.class);

		Room room2 = new Room();
		room2.setCapacity(200);
		room2.setNumberOfArtwork(0);
		room2.canSetRoomID = true;
		ResponseEntity<Room> response2 = client.postForEntity("/room", room2, Room.class);
		
		
		Room room3 = new Room();
		room3.setCapacity(200);
		room3.setNumberOfArtwork(0);
		room3.canSetRoomID = true;
		ResponseEntity<Room> response3 = client.postForEntity("/room", room3, Room.class);
		
		Room room4 = new Room();
		room4.setCapacity(200);
		room4.setNumberOfArtwork(0);
		room4.canSetRoomID = true;
		ResponseEntity<Room> response4 = client.postForEntity("/room", room4, Room.class);
		
		Room room5 = new Room();
		room5.setCapacity(200);
		room5.setNumberOfArtwork(0);
		room5.canSetRoomID = true;
		ResponseEntity<Room> response5 = client.postForEntity("/room", room5, Room.class);
		
		Room Bigroom1 = new Room();
		Bigroom1.setCapacity(500);
		Bigroom1.setNumberOfArtwork(0);
		Bigroom1.canSetRoomID = true;
		ResponseEntity<Room> response6 = client.postForEntity("/room", Bigroom1, Room.class);
		
		
		Room Bigroom2 = new Room();
		Bigroom2.setCapacity(500);
		Bigroom2.setNumberOfArtwork(0);
		Bigroom2.canSetRoomID = true;
		ResponseEntity<Room> response7 = client.postForEntity("/room", Bigroom2, Room.class);
		
		Room Bigroom3 = new Room();
		Bigroom3.setCapacity(500);
		Bigroom3.setNumberOfArtwork(0);
		Bigroom3.canSetRoomID = true;
		ResponseEntity<Room> response8 = client.postForEntity("/room", Bigroom3, Room.class);
		
		Room Bigroom4 = new Room();
		Bigroom4.setCapacity(500);
		Bigroom4.setNumberOfArtwork(0);
		Bigroom4.canSetRoomID = true;
		ResponseEntity<Room> response9 = client.postForEntity("/room", Bigroom4, Room.class);
		
		
		Room Bigroom5 = new Room();
		Bigroom5.setCapacity(500);
		Bigroom5.setNumberOfArtwork(0);
		Bigroom5.canSetRoomID = true;
		ResponseEntity<Room> response10 = client.postForEntity("/room", Bigroom5, Room.class);
		
		
		Room storage = new Room();
		storage.setCapacity(Integer.MAX_VALUE);
		storage.setNumberOfArtwork(0);
		storage.canSetRoomID = true;
		ResponseEntity<Room> response11 = client.postForEntity("/room", storage, Room.class);
		
		assertNotNull(response1);
		assertEquals(HttpStatus.CREATED, response1.getStatusCode(), "Response has correct status");
		assertNotNull(response1.getBody(), "Response has body");
		Room roomy2 = response1.getBody();
		assertEquals(200, response1.getBody().getCapacity(), "Response has correct capacity");
		assertNotNull(response1.getBody().getRoomID(), "Response has valid ID");
		return response1.getBody().getRoomID();
	}
	
	private void testGetRoom(int id) {
		ResponseEntity<Room> response = client.getForEntity("/room/" + id, Room.class);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode(), "Response has correct status");
		assertNotNull(response.getBody(), "Response has body");
		assertEquals(200, response.getBody().getCapacity(), "Response has correct name");
		assertTrue(response.getBody().getRoomID() == id, "Response has correct ID");
	}

}