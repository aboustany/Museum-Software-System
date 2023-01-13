/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;

import javax.persistence.Entity;

// line 71 "../../../../../MuseumSystem.ump"
@Entity
public class LoanableArtwork extends Artwork
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LoanableArtwork Attributes
  private boolean isAvailable;
  private int rentalFee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoanableArtwork()
  {
    super();
    isAvailable = false;
    rentalFee = 0;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public String toString()
  {
    return super.toString() + "["+
            "isAvailable" + ":" + getIsAvailable()+ "," +
            "rentalFee" + ":" + getRentalFee()+ "]";
  }
}