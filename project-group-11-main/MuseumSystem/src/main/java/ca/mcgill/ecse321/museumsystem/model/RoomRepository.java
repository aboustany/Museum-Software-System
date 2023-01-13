package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.Room;

public interface RoomRepository extends CrudRepository<Room, String>{
	Room findRoomByRoomID(int roomID);
}
