/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@MappedSuperclass
// line 14 "../../../../../MuseumSystem.ump"
public abstract class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String userName;
  private String password;
//  /**
//   * @Id lazy immutable Integer accountID;
//   */
 // @GeneratedValue()
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int accountID;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account() {
	  
  }
  
  public Account(int aAccountID)
  {
    userName = null;
    password = null;
    accountID = aAccountID;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setAccountID(int aAccountID)
  {
    boolean wasSet = false;
    accountID = aAccountID;
    wasSet = true;
    return wasSet;
  }

  
  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public int getAccountID()
  {
    return accountID;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "userName" + ":" + getUserName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "accountID" + ":" + getAccountID()+ "]";
  }
}