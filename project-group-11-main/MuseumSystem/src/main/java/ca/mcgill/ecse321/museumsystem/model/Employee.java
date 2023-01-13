/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// line 34 "../../../../../MuseumSystem.ump"
public class Employee extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------


  //------------------------
  // CONSTRUCTOR
  //------------------------

	
  public Employee()
  {
    super();
  }

  /* Code from template association_SetOneToMany */
  

  public void delete()
  {
    super.delete();
  }

}