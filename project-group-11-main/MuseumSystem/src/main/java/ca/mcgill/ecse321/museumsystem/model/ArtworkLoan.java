/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.museumsystem.model;
import javax.persistence.*;

@Entity
// line 52 "../../../../../MuseumSystem.ump"
public class ArtworkLoan
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ArtworkLoan Attributes
  private String startDay;
  private String endDay;
  private boolean isApproved;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int loanID;

  //ArtworkLoan Associations
  @ManyToOne(optional = false)
  private Visitor visitor;
  @ManyToOne(optional = false)
  private LoanableArtwork loanableArtwork;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ArtworkLoan() {
	  
  }
  
  public ArtworkLoan(Visitor aVisitor, LoanableArtwork aLoanableArtwork)
  {
    startDay = null;
    endDay = null;
    isApproved = false;
    if (!setVisitor(aVisitor))
    {
      throw new RuntimeException("Unable to create ArtworkLoan due to aVisitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setLoanableArtwork(aLoanableArtwork))
    {
      throw new RuntimeException("Unable to create ArtworkLoan due to aLoanableArtwork. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setIsApproved(boolean aIsApproved)
  {
    boolean wasSet = false;
    isApproved = aIsApproved;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetImmutable */
  public boolean setLoanID(int aLoanID)
  {
    boolean wasSet = false;
    loanID = aLoanID;
    wasSet = true;
    return wasSet;
  }

  public String getStartDay()
  {
    return startDay;
  }

  public String getEndDay()
  {
    return endDay;
  }

  public boolean getIsApproved()
  {
    return isApproved;
  }

  public int getLoanID()
  {
    return loanID;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsApproved()
  {
    return isApproved;
  }
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_GetOne */
  public LoanableArtwork getLoanableArtwork()
  {
    return loanableArtwork;
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setLoanableArtwork(LoanableArtwork aNewLoanableArtwork)
  {
    boolean wasSet = false;
    if (aNewLoanableArtwork != null)
    {
      loanableArtwork = aNewLoanableArtwork;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    visitor = null;
    loanableArtwork = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "startDay" + ":" + getStartDay()+ "," +
            "endDay" + ":" + getEndDay()+ "," +
            "isApproved" + ":" + getIsApproved()+ "," +
            "loanID" + ":" + getLoanID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "loanableArtwork = "+(getLoanableArtwork()!=null?Integer.toHexString(System.identityHashCode(getLoanableArtwork())):"null");
  }
}