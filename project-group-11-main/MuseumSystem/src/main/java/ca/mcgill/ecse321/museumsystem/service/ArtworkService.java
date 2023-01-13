package ca.mcgill.ecse321.museumsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.LoanableArtwork;
import ca.mcgill.ecse321.museumsystem.model.Room;

import ca.mcgill.ecse321.museumsystem.exception.ArtworkException;;

/**
 * 
 * @author Anthony Boustany
 * 
 * Artwork Service class.
 *
 */

@Service
public class ArtworkService {

    @Autowired
    ArtworkRepository artworkRepository;

    @Transactional
    public Artwork createArtwork(Artwork artwork){
        
        artwork = artworkRepository.save(artwork);
        return artwork;
    }
    


    @Transactional
    public LoanableArtwork createLoanableArtwork(LoanableArtwork loanableArtwork){
       
        loanableArtwork = artworkRepository.save(loanableArtwork);
        return loanableArtwork; 
    }
    
    @Transactional
	public Iterable<Artwork> getAllArtworks() {
		return artworkRepository.findAll();
	}

    @Transactional
    public Artwork getArtworkById(int artworkID){
        Artwork artwork = artworkRepository.findArtworkByArtworkID(artworkID);

        // if(artwork == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "Artwork not found.");
        // }

        return artwork;
    }

    @Transactional
    public void deleteArtworkById(int artworkID){
        Artwork artwork = artworkRepository.findArtworkByArtworkID(artworkID);

        // if(artwork == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "Artwork not found.");
        // }

        artworkRepository.delete(artwork);
        
    }


    //Optional, we can use getArtworkById() too.
//    @Transactional
//    public LoanableArtwork getLoanableArtworkById(int artworkID){
//        LoanableArtwork loanableArtwork = artworkRepository.findLoanableArtworkByArtworkID(artworkID);
//        if(loanableArtwork == null){
//            throw new ArtworkException(HttpStatus.NOT_FOUND, "Artwork not found.");
//        }
//        return loanableArtwork;
//    }


    @Transactional
    public List<Artwork> getAllLoanableArtworks(){
        List<Artwork> allArtworks = toList(artworkRepository.findAll());
        List<Artwork> allLoanableArtworks = new ArrayList<Artwork>();
        for (Artwork artwork: allArtworks){
            //Not sure about this at all, trying to check class type.
            if(artwork.getClass().toString() == "LoanableArtwork"){
                allLoanableArtworks.add(artwork);
            }
        }

        if(allLoanableArtworks.isEmpty()){
            throw new ArtworkException(HttpStatus.NOT_FOUND, "No Loanable Artworks found.");
        }

        return allLoanableArtworks;
    }
    

    @Transactional
    public LoanableArtwork makeLoanableArtwork(Artwork artwork, int rentalFee, boolean isAvailable){
        LoanableArtwork loanableArtwork = new LoanableArtwork();
        loanableArtwork.setArtworkID(artwork.getArtworkID());
        loanableArtwork.setRoom(artwork.getRoom());
        loanableArtwork.setOnDisplay(artwork.getOnDisplay());
        loanableArtwork.setRentalFee(rentalFee);
        loanableArtwork.setIsAvailable(isAvailable);
        artworkRepository.delete(artwork);
        artworkRepository.save(loanableArtwork);
        return loanableArtwork;
    }

    @Transactional
    public Artwork removeLoanableArtwork(LoanableArtwork loanableArtwork){
        Artwork artwork = new Artwork();
        artwork.setArtworkID(loanableArtwork.getArtworkID());
        artwork.setRoom(loanableArtwork.getRoom());
        artwork.setOnDisplay(loanableArtwork.getOnDisplay());
        artworkRepository.delete(loanableArtwork);
        artworkRepository.save(artwork);
        return artwork;
    }


    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}