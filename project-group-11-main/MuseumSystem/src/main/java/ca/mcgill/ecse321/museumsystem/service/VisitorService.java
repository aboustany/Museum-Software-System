package ca.mcgill.ecse321.museumsystem.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;


/**
 * 
 * @author Mehdi Ammor
 * 
 * Service for Visitor class.
 *
 */
@Service
public class VisitorService {
	
	@Autowired
	VisitorRepository visitorRepository;

	//check if visitor id  exists, otherwise return visitor
	@Transactional
	public Visitor getVisitorByID(int id) {
		
		Visitor visitor = visitorRepository.findVisitorByAccountID(id);
		if (visitor == null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Visitor doesn't exist");
		}
		return visitor;	
		
	}
	//deletes visitor from visitor Repository
	@Transactional
	public void deleteVisitor(Visitor visitor) {
		visitorRepository.delete(visitor);
	}
	
	//check if visitor ID is null or if it's already in database, otherwise, return visitor. 
	@Transactional
	public Visitor createVisitor(Visitor visitor) {
		//check if visitor is not null
		if(visitor==null) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Visitor cannot be null");
		}
		visitor = visitorRepository.save(visitor);
		return visitor;
	}
	
	@Transactional
	public Visitor UpdateVisitorUsername( int VisitorID, String password,  String newUsername ) {
		Visitor visitor = visitorRepository.findVisitorByAccountID(VisitorID);
		//check if visitor exists
		if (visitor == null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Visitor doesn't exist");
		}
		//GivenPassword is wrong
		if (!password.equals(visitor.getPassword())) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "Wrong Password.");
		}
		//check if username  has @ and .
		if (checkValidUsername(newUsername) == false) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "Invalid Username. The email address must contain a @ and a '.' , Please try again.");
		}
		//otherwise, update username and save in repository
		visitor.setUserName(newUsername);
		visitorRepository.save(visitor);
		return visitor;
	}
	
	@Transactional
	public Visitor UpdateVisitorPassword( int visitorID, String currentPassword, String newPassword ) {
		Visitor visitor = visitorRepository.findVisitorByAccountID(visitorID);
		//check if employee exists
		if (visitor == null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Visitor doesn't exist");
		}
		//GivenPassword is wrong
		if (!currentPassword.equals(visitor.getPassword())) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "Wrong Password.");
		}
		//check if password has more than 8 characters
		if (checkValidPassword(newPassword) == false) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "Invalid Password. The password must have a minimum of 8 characters");
		}
		//check if the newPassword is the same as the currentPassword
		if (currentPassword.equals(newPassword)) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "The new password cannot be the same as the old password");
		}
		//otherwise, update username and save in repository
		visitor.setPassword(newPassword);
		visitorRepository.save(visitor);
		return visitor;
	}	
	
	
	 @Transactional
	    public ArrayList<Visitor> getAllVisitors() {
	        return (ArrayList<Visitor>) visitorRepository.findAll();
	    }

	 
	//Helper function to check if username is valid ( contains @ and . )
		public boolean checkValidUsername (String Username) {
			int ad = 0;
			int point= 0;
			for (int i=0; i<Username.length(); i++) {
				if (Username.charAt(i) == '@') {
					ad++;
				}
				if (Username.charAt(i) == '.') {
					point++;
				}
			}
			//check if @ and .
			if (ad == 1 && point==1) {
				return true;
			}
			return false;
		}
		//Helper functon to check if password is less than 8 characters
		public boolean checkValidPassword (String Password) {
			if (Password.length()<=8) {
				return false;
			}
			return true;
		}

}