package ca.mcgill.ecse321.museumsystem.service;

import java.text.SimpleDateFormat;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;


//Author @Mehdi Ammor

//Museum System Services Implemented
@Service
public class MuseumSystemService {
	
	@Autowired
	MuseumSystemRepository museumSystemRepository;
	
	//helper for checking valid date
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
	
	//check if museum already exists in repository, otherwise return museum
	@Transactional
	public MuseumSystem getMuseumSystemByID(int id) {
		
	MuseumSystem museum = museumSystemRepository.findMuseumSystemByMuseumID(id);
	if (museum==null) {
		throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Museum doesn't exist");
	}
	return museum;
		
	}
	//Create museum
	@Transactional
	public MuseumSystem createMuseumSystem(MuseumSystem museum) {
	museum = museumSystemRepository.save(museum);
	return museum;
	}

	//Checks if museum exists, if input hours are valid and weather hours aren't the same. if so, set opening hours
	//Then save in repository updated museum. 
	@Transactional
	public MuseumSystem SetOpeningHours(int museumId, String openingHours) {
		//must also make sure that opening and closing hours are appropriate inputs. 
		// must check that closing hours are before opening hours 
		MuseumSystem museum = museumSystemRepository.findMuseumSystemByMuseumID(museumId);
		boolean op = isValid(openingHours);
	
		if (museum ==null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Museum doesn't exist");
		}
	
		if (op==false) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Invalid OpeningHours. Try again with a format 'hh:mm' ");
		}
		
		if(openingHours.equals(museum.getClosingHour())) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "OpeningHours and ClosingHours cannot be the same");
		}
		museum.setOpeningHour(openingHours);
		return museum;
		
	}
	//Checks if museum exists, if input hours are valid and weather hours aren't the same. if so, set closing hours
	//Then save in repository updated museum. 
	@Transactional
	public MuseumSystem SetClosingHours(int museumId, String closingHours) {
		//must also make sure that opening and closing hours are appropriate inputs. 
		// must check that closing hours are before opening hours 
		MuseumSystem museum = museumSystemRepository.findMuseumSystemByMuseumID(museumId);
		boolean cl = isValid(closingHours);
		
		if (museum ==null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Museum doesn't exist");
		}
		
		if (cl==false) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Invalid ClosingHours. Try again with a format 'hh:mm' ");
		}
		
		if(closingHours.equals(museum.getOpeningHour())) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "OpeningHours and ClosingHours cannot be the same");
		}
	
		museum.setClosingHour(closingHours);
		return museum;
		
	}

	//Check if museum exists, if the visitorFee is higher than 0 and weather visitor fee is not already set to that value. 
	@Transactional
	public MuseumSystem SetVisitorFee(int museumId, int VisitorFee) {
		
		MuseumSystem museum = museumSystemRepository.findMuseumSystemByMuseumID(museumId);

		if (museum ==null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Museum doesn't exist");
		}
		
		if(VisitorFee<0) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "VisitorFee cannot be negative");
		}
		
		if(museum.getVisitorFee()==VisitorFee) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "VisitorFee already set to " + String.valueOf(VisitorFee));
		}
		
		museum.setVisitorFee(VisitorFee);
		return museum;
		
	}
	//Helper function to check if date is valid. 
	public static boolean isValid(String date3){
		try {
	        simpleDateFormat.parse(date3);
	    }
		catch (Exception e) {
	        return false;
	    }
	    return true;
	}

}
