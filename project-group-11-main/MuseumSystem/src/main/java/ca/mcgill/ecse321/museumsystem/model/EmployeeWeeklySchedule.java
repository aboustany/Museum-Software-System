/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
// line 90 "../../../../../MuseumSystem.ump"
public class EmployeeWeeklySchedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EmployeeWeeklySchedule Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  private int scheduleID;
  private String startDay;
  private String endDay;

  //EmployeeWeeklySchedule Associations
  @ManyToOne(optional = false)
  private Owner owner;
  @ManyToOne(optional = false)
  private Employee employee;


  //------------------------
  // CONSTRUCTOR
  //------------------------
  public EmployeeWeeklySchedule() {
	  
  }
  
  public EmployeeWeeklySchedule(Owner aOwner, Employee aEmployee)
  {
    startDay = null;
    endDay = null;
    if (!setOwner(aOwner))
    {
      throw new RuntimeException("Unable to create EmployeeWeeklySchedule due to aOwner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setEmployee(aEmployee))
    {
      throw new RuntimeException("Unable to create EmployeeWeeklySchedule due to aEmployee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
  public boolean setScheduleID(int aScheduleID)
  {
    boolean wasSet = false;
    scheduleID = aScheduleID;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDay(String aStartDay)
  {
    boolean wasSet = false;
    startDay = aStartDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDay(String aEndDay)
  {
    boolean wasSet = false;
    endDay = aEndDay;
    wasSet = true;
    return wasSet;
  }

 
  public int getScheduleID()
  {
    return scheduleID;
  }

  public String getStartDay()
  {
    return startDay;
  }

  public String getEndDay()
  {
    return endDay;
  }
  /* Code from template association_GetOne */
  public Owner getOwner()
  {
    return owner;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setOwner(Owner aNewOwner)
  {
    boolean wasSet = false;
    if (aNewOwner != null)
    {
      owner = aNewOwner;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setEmployee(Employee aNewEmployee)
  {
    boolean wasSet = false;
    if (aNewEmployee != null)
    {
      employee = aNewEmployee;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    owner = null;
    employee = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "scheduleID" + ":" + getScheduleID()+ "," +
            "startDay" + ":" + getStartDay()+ "," +
            "endDay" + ":" + getEndDay()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}