/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
//  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// line 25 "../../../../../MuseumSystem.ump"
public class Owner extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Owner Associations
   // private MuseumSystem museum;

  //------------------------
  // CONSTRUCTOR
  //------------------------

//  public Owner(int aAccountID, MuseumSystem aMuseum)
//  {
//    super(aAccountID);
//    if (aMuseum == null || aMuseum.getOwner() != null)
//    {
//      throw new RuntimeException("Unable to create Owner due to aMuseum. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
//    }
//    museum = aMuseum;
//  }

  public Owner()
  {
    super();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
//  public MuseumSystem getMuseum()
//  {
//    return museum;
//  }



}