package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.Artwork;


public interface ArtworkRepository extends CrudRepository<Artwork, String>{
	Artwork findArtworkByArtworkID(int artworkID);
//	LoanableArtwork findLoanableArtworkByArtworkID(int artworkID);
}
