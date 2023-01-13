/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
// line 79 "../../../../../MuseumSystem.ump"
public class Room
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Room Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  private int roomID;
  private int capacity;
  private int numberOfArtwork;

  //Room Associations
//  @ManyToOne(optional = false)
//  private MuseumSystem museum;

  //Helper Variables
  public boolean canSetRoomID;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public Room() {}
  


  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
  public boolean setRoomID(int aRoomID)
  {
    boolean wasSet = false;
    //if (!canSetRoomID) { return false; }
    //canSetRoomID = false;
    roomID = aRoomID;
    wasSet = true;
    return wasSet;
  }

  public boolean setCapacity(int aCapacity)
  {
    boolean wasSet = false;
    capacity = aCapacity;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfArtwork(int aNumberOfArtwork)
  {
    boolean wasSet = false;
    numberOfArtwork = aNumberOfArtwork;
    wasSet = true;
    return wasSet;
  }

 
  public int getRoomID()
  {
    return roomID;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public int getNumberOfArtwork()
  {
    return numberOfArtwork;
  }
  
 

}