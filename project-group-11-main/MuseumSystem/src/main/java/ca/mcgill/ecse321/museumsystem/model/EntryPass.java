/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// line 111 "../../../../../MuseumSystem.ump"
@Entity
public class EntryPass
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EntryPass Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  private int passID;
  private int price;
  private String date;

  //EntryPass Associations
  @ManyToOne(optional = false)
  private Visitor visitor;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EntryPass()
  {
//    passID = aPassID;
//    price = aPrice;
//    date = aDate;
//    if (!setVisitor(aVisitor))
//    {
//      throw new RuntimeException("Unable to create EntryPass due to aVisitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
//    }
//    boolean didAddMuseum = setMuseum(aMuseum);
//    if (!didAddMuseum)
//    {
//      throw new RuntimeException("Unable to create pass due to museum. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
//    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassID(int aPassID)
  {
    boolean wasSet = false;
    passID = aPassID;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public int getPassID()
  {
    return passID;
  }

  public int getPrice()
  {
    return price;
  }

  public String getDate()
  {
    return date;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }

  /* Code from template association_SetUnidirectionalOne */
  public boolean setVisitor(Visitor aNewVisitor)
  {
    boolean wasSet = false;
    if (aNewVisitor != null)
    {
      visitor = aNewVisitor;
      wasSet = true;
    }
    return wasSet;
  }

}