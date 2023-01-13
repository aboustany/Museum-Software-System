package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.Artwork;

public interface ArtworkLoanRepository extends CrudRepository<ArtworkLoan, String>{
	ArtworkLoan findArtworkLoanByLoanID(int loanID);

}
