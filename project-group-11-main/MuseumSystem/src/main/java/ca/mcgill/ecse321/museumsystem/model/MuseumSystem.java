/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;
import java.util.*;

@Entity
// line 4 "../../../../../MuseumSystem.ump"
public class MuseumSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MuseumSystem Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int museumID;
  private String openingHour;
  private String closingHour;
  private int visitorFee;

  //MuseumSystem Associations
  @OneToOne(optional=false)
  private Owner owner;
 

  
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MuseumSystem()
  {

  }
  
  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
 
  
  public boolean setOpeningHour(String aOpeningHour)
  {
    boolean wasSet = false;
    openingHour = aOpeningHour;
    wasSet = true;
    return wasSet;
  }
 
//  public boolean setClosingHour(String aClosingHour)
//  {
//    
//  }

  public boolean setVisitorFee(int aVisitorFee)
  {
    boolean wasSet = false;
    visitorFee = aVisitorFee;
    wasSet = true;
    return wasSet;
  }

  
  public int getMuseumID()
  {
    return museumID;
  }

  public String getOpeningHour()
  {
    return openingHour;
  }

  public String getClosingHour()
  {
    return closingHour;
  }

  public int getVisitorFee()
  {
    return visitorFee;
  }
  /* Code from template association_GetOne */
  public Owner getOwner()
  {
    return owner;
  }

public boolean setClosingHour(String aClosingHour) {
	// TODO Auto-generated method stub
	boolean wasSet = false;
    closingHour = aClosingHour;
    wasSet = true;
    return wasSet;
}


 
}