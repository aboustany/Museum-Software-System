package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.museumsystem.dto.ArtworkLoanDto;
import ca.mcgill.ecse321.museumsystem.dto.MultipleArtworkLoanResponseDto;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.service.ArtworkLoanService;

/**
 * Controller for ArtworkLoan.
 * 
 * @author Sami Ferneini
 *
 */


@CrossOrigin
@RestController
public class ArtworkLoanController{
	
	@Autowired
	private ArtworkLoanService artworkLoanService;
	
	@GetMapping("/artwork_loan/{id}")
	public ResponseEntity<ArtworkLoan> getArtworkLoanByID(@PathVariable int loanID){
		
		ArtworkLoan artworkLoan = artworkLoanService.getArtworkLoanByID(loanID);
		
		return new ResponseEntity<ArtworkLoan>(artworkLoan, HttpStatus.OK);
	}

	@GetMapping("/artwork_loan")
	public ResponseEntity<MultipleArtworkLoanResponseDto> getAllPendingArtworkLoans(){
		Iterable<ArtworkLoan> loans = artworkLoanService.getAllPendingLoans();
		MultipleArtworkLoanResponseDto response = new MultipleArtworkLoanResponseDto(loans);
		return new ResponseEntity<MultipleArtworkLoanResponseDto>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/artwork_loan", consumes = {"application/json"})
	public ResponseEntity<ArtworkLoan> createArtworkLoan(@RequestBody ArtworkLoan artworkLoan){
		artworkLoan = artworkLoanService.createArtworkLoan(artworkLoan);
		return new ResponseEntity<ArtworkLoan>(artworkLoan, HttpStatus.CREATED);
	}
	

	@DeleteMapping("/artwork_loan/{id}")
	public ResponseEntity<String> deleteArtworkLoanByID(@PathVariable int loanID){
		
		ArtworkLoan artworkLoan = artworkLoanService.getArtworkLoanByID(loanID);
		artworkLoanService.deleteArtworkLoan(artworkLoan);
		
		return new ResponseEntity<String>("Artwork Loan Deleted Successfully", HttpStatus.OK);
	}

	
}





	
	
