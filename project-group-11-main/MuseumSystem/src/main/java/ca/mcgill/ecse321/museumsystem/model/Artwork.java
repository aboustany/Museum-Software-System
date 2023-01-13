/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
// line 62 "../../../../../MuseumSystem.ump"
public class Artwork
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artwork Attributes
  private String name;
  private boolean isAvailable;
  private int rentalFee;
  private boolean onDisplay;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int artworkID;
  
  //Artwork Associations
  @ManyToOne(optional = true)
  private Room room;
//  @ManyToOne(optional = false)
//  private MuseumSystem museum;

  //Helper Variables
  public String imageURL;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Artwork() {
	  
  }

  //------------------------
  // INTERFACE
  //------------------------
  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setOnDisplay(boolean aOnDisplay)
  {
    boolean wasSet = false;
    onDisplay = aOnDisplay;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetImmutable */
  public boolean setArtworkID(int aArtworkID)
  {
    boolean wasSet = false;
    artworkID = aArtworkID;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  
  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setRentalFee(int aRentalFee)
  {
    boolean wasSet = false;
    rentalFee = aRentalFee;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public int getRentalFee()
  {
    return rentalFee;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsAvailable()
  {
    return isAvailable;
  }

  public boolean getOnDisplay()
  {
    return onDisplay;
  }

  public int getArtworkID()
  {
    return artworkID;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isOnDisplay()
  {
    return onDisplay;
  }
  /* Code from template association_GetOne */
  public Room getRoom()
  {
    return room;
  }
  
  /* Code from template association_SetUnidirectionalOne */
  public boolean setRoom(Room aNewRoom)
  {
    boolean wasSet = false;
    if (aNewRoom != null)
    {
      room = aNewRoom;
      wasSet = true;
    }
    return wasSet;
  }

}