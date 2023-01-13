/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// line 43 "../../../../../MuseumSystem.ump"
public class Visitor extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Visitor()
  {
    super();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}