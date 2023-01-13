package ca.mcgill.ecse321.museumsystem.dto;

/**
 * 
 * @author Anthony Boustany
 * 
 * Loanable Artwork DTO class.
 *
 */

public class LoanableArtworkDto extends ArtworkDto {
    
    private boolean isAvailable;
    private int rentalFee;

    //Not sure if I keep super() or leave it empty.
    public LoanableArtworkDto(){ 
        super();
    }


    public LoanableArtworkDto(String name, boolean onDisplay, RoomDto room, boolean isAvailable, int rentalFee){
        this.name = name;
        this.onDisplay = onDisplay;
        this.room = room;
        this.isAvailable = isAvailable;
        this.rentalFee = rentalFee;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public int getRentalFee(){
        return rentalFee;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void setRentalFee(int rentalFee){
        this.rentalFee = rentalFee;
    }

}