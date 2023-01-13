package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;

/**
 * Dto for ArtworkLoan.
 * 
 * @author Sami Ferneini
 *@param string startDay
 *@param string endDay
 *@param boolean isApproved
 *@param int loanID
 */

public class ArtworkLoanDto {

	
	private String startDay;
	private String endDay;
	private boolean isApproved;
	private int loanID;
	
	public ArtworkLoanDto(ArtworkLoan artworkLoan) {
		this.startDay = artworkLoan.getStartDay();
		this.endDay = artworkLoan.getEndDay();
		this.isApproved = artworkLoan.getIsApproved();
		this.loanID = artworkLoan.getLoanID();
	}
	
	public String getStartDay() {
		return this.startDay;
	}
	
	public String getEndDay() {
		return this.endDay;
	}
	
	public boolean isIsApproved() {
		return this.isApproved;
	}
	
	public int getLoanID(){
		return this.loanID;
	}
}