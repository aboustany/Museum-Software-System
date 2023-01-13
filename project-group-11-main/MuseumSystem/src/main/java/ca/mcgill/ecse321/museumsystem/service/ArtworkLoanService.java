package ca.mcgill.ecse321.museumsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoanRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.Visitor;



/**
 * Service for ArtworkLoan.
 * 
 * @author Sami Ferneini
 *
 */

@Service
public class ArtworkLoanService {

	@Autowired
	ArtworkLoanRepository artworkLoanRepo;
	

	@Transactional // Simply creates an Artwork
	public ArtworkLoan createArtworkLoan(ArtworkLoan artworkLoan) {
		if (artworkLoan.getStartDay() != null || artworkLoan.getEndDay() != null || artworkLoan.getLoanID() != 0) { // Some basic checks to make sure the loan is valid 
		artworkLoan = artworkLoanRepo.save(artworkLoan); // save if the loan is valid
		return artworkLoan;
		}else {
		return null; // if the loan input is invalid, return null
		}
	}
	
	@Transactional
	public ArtworkLoan getArtworkLoanByID(int loanID) {
		try {
		ArtworkLoan artworkLoan = artworkLoanRepo.findArtworkLoanByLoanID(loanID);
		return artworkLoan;
		}
		catch(Exception e) {
			System.out.println("Artwork Loan not found!");
		}
		return null;
	}
	
	@Transactional
	public void deleteArtworkLoan(ArtworkLoan loan) {
		artworkLoanRepo.delete(loan);
	}

	@Transactional
	public Iterable<ArtworkLoan> getAllArtworkLoans() {
		return artworkLoanRepo.findAll();
	}
	
	
	@Transactional // Simply approve loan
	public void approveLoan(ArtworkLoan loan) {
		loan.setIsApproved(true);
	}

	@Transactional
	public Iterable<ArtworkLoan> getAllPendingLoans(){
		ArrayList<ArtworkLoan> pendingLoans = new ArrayList<ArtworkLoan>();
		Iterable<ArtworkLoan> allLoans = artworkLoanRepo.findAll();
		Iterable<ArtworkLoan> allPendingLoans;

		for(ArtworkLoan loan: allLoans){
			if(!loan.isIsApproved()){
				pendingLoans.add(loan);
			}
		}

		allPendingLoans = pendingLoans;

		return allPendingLoans;
	}
	
	
	}

	
	
	
	
	
	
