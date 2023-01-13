package ca.mcgill.ecse321.museumsystem.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;


/**
 * 
 * @author Elsa Chelala
 * 
 * Service for Room class.
 *
 */
@Service
public class RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Transactional
	public Room getRoomById(int id) {	
		Room room = roomRepository.findRoomByRoomID(id);
		
		if(room == null) {
			System.out.println("Room not found.");
		}
		return room;	
	}
	
	@Transactional
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}
	
	@Transactional
	public Room createRoom(Room room) {
		room = roomRepository.save(room);
		return room;
	}

	@Transactional
    public ArrayList<Room> createRooms() {
        ArrayList<Room> allRooms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Room smallRoom = new Room();
            smallRoom.setCapacity(200);
            allRooms.add(smallRoom);
            roomRepository.save(smallRoom);
            Room bigRoom = new Room();
            bigRoom.setCapacity(300);
            allRooms.add(bigRoom);
            roomRepository.save(bigRoom);
        }
        Room storage = new Room();
        storage.setCapacity(Integer.MAX_VALUE);
        allRooms.add(storage);
        roomRepository.save(storage);
        return allRooms;
    }

	@Transactional
    public boolean canAddArtwork(int id) {
        Room room = getRoomById(id);
        if(room.getCapacity() < room.getNumberOfArtwork()){
			return true;
		}
		else {
			return false; 
		}
    }

    @Transactional
    public ArrayList<Room> getAllRooms() {
        return (ArrayList<Room>) roomRepository.findAll();
    }
}