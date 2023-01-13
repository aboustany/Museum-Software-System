

package ca.mcgill.ecse321.museumsystem.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoanRepository;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklyScheduleRepository;
import ca.mcgill.ecse321.museumsystem.model.EntryPass;
import ca.mcgill.ecse321.museumsystem.model.EntryPassRepository;
import ca.mcgill.ecse321.museumsystem.model.LoanableArtwork;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystemRepository;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.OwnerRepository;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;
import ca.mcgill.ecse321.museumsystem.model.Visitor;



/* Things the owner must do 
REQ4.1: The museum management system shall allow the owner to set opening hours of the museum.
REQ4.2: The museum management system shall allow the owner to manage the employees’ schedules.
REQ4.3: The museum management system shall allow the owners to set the price of a visitor pass.
REQ4.4: The museum management system shall allow the owners to set the price for the loan of an artwork.
REQ4.5: The museum management system shall allow the owners to decide each artwork's location (which room).
 */

/**
 * 
 * @author Mehdi
 * 
 * Service for Owner class.
 *
 */

@Service
public class OwnerService {
	
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	MuseumSystemRepository museumRepository;
	@Autowired
	EntryPassRepository entryPassRepository;
	@Autowired
	ArtworkRepository artWorkRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeWeeklyScheduleRepository employeeWeeklyScheduleRepository;
	
	EmployeeService employeeService;
	
	private String dateFormat;
	
	//returns owner by AccountId
	@Transactional
	public Owner getOwnerbyID(int id) {
		Owner owner = ownerRepository.findOwnerByAccountID(id);
		if (owner == null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Owner doesn't exist");
		
		}
		return owner;	
	}

	//Creates an owner
	@Transactional
	public Owner createOwner(Owner owner) {
		
		if(owner==null) {
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Owner cannot be null");
		}
		owner = ownerRepository.save(owner);
		return owner;
	}
	
	//deletes Owner from visitor Repository
	@Transactional
	public void deleteOwner(Owner owner) {
		ownerRepository.delete(owner);
	}
	
	@Transactional
	public Owner UpdateOwnerUsername( int OwnerID, String password,  String newUsername ) {
		Owner owner = ownerRepository.findOwnerByAccountID(OwnerID);
		//check if owner exists
		if (owner == null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Owner doesn't exist");
		}
		//GivenPassword is wrong
		if (!password.equals(owner.getPassword())) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "Wrong Password.");
		}
		//check if username  has @ and .
		if (checkValidUsername(newUsername) == false) {
			throw new MuseumSystemException(HttpStatus.NOT_ACCEPTABLE, "Invalid Username. The email address must contain a @ and a '.' , Please try again.");
		}
		//otherwise, update username and save in repository
		owner.setUserName(newUsername);
		ownerRepository.save(owner);
		return owner;
	}
	
	@Transactional
	public Owner UpdateOwnerPassword( int OwnerID, String currentPassword, String newPassword ) {
		Owner owner = ownerRepository.findOwnerByAccountID(OwnerID);
		//check if employee exists
		if (owner == null) {
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Owner doesn't exist");
		}
		//GivenPassword is wrong
		if (!currentPassword.equals(owner.getPassword())) {
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
		owner.setPassword(newPassword);
		ownerRepository.save(owner);
		return owner;
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