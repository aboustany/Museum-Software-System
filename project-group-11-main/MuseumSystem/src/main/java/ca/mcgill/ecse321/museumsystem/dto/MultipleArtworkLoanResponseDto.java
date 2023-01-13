package ca.mcgill.ecse321.museumsystem.dto;

import java.util.ArrayList;

import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;

public class MultipleArtworkLoanResponseDto {
	
	private Iterable<ArtworkLoan> artworkLoans;
	
	public MultipleArtworkLoanResponseDto(Iterable<ArtworkLoan> artworkLoans) {
		ArrayList<ArtworkLoan> artworkLoan = new ArrayList<ArtworkLoan>();
		for (ArtworkLoan e : artworkLoans) {
			artworkLoan.add(e);
		}
		this.artworkLoans = artworkLoan;
	}
	
	public Iterable<ArtworkLoan> getArtworkLoans() {
		return this.artworkLoans;
	}

}