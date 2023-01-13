package ca.mcgill.ecse321.museumsystem.dto;

import java.util.ArrayList;
import ca.mcgill.ecse321.museumsystem.model.Room;

public class MultipleRoomResponseDto {
	
	private Iterable<Room> rooms;
	
	public MultipleRoomResponseDto(Iterable<Room> rooms) {
		ArrayList<Room> room = new ArrayList<Room>();
		for (Room e : rooms) {
			room.add(e);
		}
		this.rooms = room;
	}
	
	public Iterable<Room> getRooms() {
		return this.rooms;
	}

}
