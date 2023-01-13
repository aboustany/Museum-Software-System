package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.Room;


/**
 * 
 * @author Elsa
 * Data transfer object for Room class.
 * @param int id
 * @param int capacity
 * @param int numberOfArtwork
 *
 */
public class RoomDto {
	
	private int id;
	
	private int capacity;

    private int numberOfArtwork;
	
	public RoomDto(Room room) {
		if(room != null) {
			this.id = room.getRoomID();
			this.capacity = room.getCapacity();
            this.numberOfArtwork = room.getNumberOfArtwork();
		}
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getCapacity() {
		return this.capacity;
	}

    public int getNumberOfArtwork(){
        return this.numberOfArtwork;
    }

}