package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.dto.RoomDto;

/**
 * 
 * @author Anthony Boustany
 * 
 * Artwork DTO class.
 *
 */

public class ArtworkDto {
    
    protected int artworkID;

    protected String name;

    protected boolean onDisplay;

    protected RoomDto room;


    public ArtworkDto(){}

    public ArtworkDto(Artwork artwork){
        
        if (artwork == null) {
            throw new IllegalArgumentException("There is no such Artwork.");
        }
        
        this.artworkID = artwork.getArtworkID();
        this.name = artwork.getName();
        this.onDisplay = artwork.getOnDisplay();
        this.room = new RoomDto(artwork.getRoom());
    }   


    //Is this needed?
    public ArtworkDto(int artworkID, String name, boolean onDisplay, RoomDto room){
        this.artworkID = artworkID;
        this.name = name;
        this.onDisplay = onDisplay;
        this.room = room;
    }

    
    public int getArtworkID(){
        return artworkID;
    }

    public String getName(){
        return name;
    }

    public boolean getOnDisplay(){
        return onDisplay;
    }

    public RoomDto getRoom(){
        return room;
    }

    public void setRoom(RoomDto room){
        this.room = room;
    }

    public void setOnDisplay(boolean onDisplay){
        this.onDisplay = onDisplay;
    }

    public void setName(String name){
        this.name = name;
    }

}