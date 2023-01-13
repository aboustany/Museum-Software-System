package ca.mcgill.ecse321.museumsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.museumsystem.exception.MuseumSystemException;
import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EmployeeRepository;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklySchedule;
import ca.mcgill.ecse321.museumsystem.model.EmployeeWeeklyScheduleRepository;
import ca.mcgill.ecse321.museumsystem.model.LoanableArtwork;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;


/**
 * 
 * @author Karim
 * 
 * Service for Employee class.
 *
 */
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeWeeklyScheduleRepository employeeWeeklyScheduleRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	ArtworkRepository artworkRepository;
	
	@Transactional
	public Employee getEmployeeById(int id) {
		
		try {
			Employee employee = employeeRepository.findEmployeeByAccountID(id);
			return employee;
		}
		
		catch(Exception e) {
			System.out.println("Employee not found.");
		}
		return null;
		
	}
	@Transactional
	public Iterable<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Transactional
	public Employee createEmployee(Employee employee) {
		employee = employeeRepository.save(employee);
		return employee;
	}
	
	@Transactional
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	@Transactional
	public EmployeeWeeklySchedule getEmployeeWeeklyScheduleById(int id) {
		try {
			EmployeeWeeklySchedule employeeWeeklySchedule = employeeWeeklyScheduleRepository.findEmployeeWeeklyScheduleByScheduleID(id);
			return employeeWeeklySchedule;
		}catch(Exception e) {
			System.out.println("Employee Weekly Schedule not found.");
		}return null;
	}
	
	@Transactional
	public void setArtworkToRoom(int artworkId, int roomId) {
		//FREQ2
		Room room = roomRepository.findRoomByRoomID(roomId);
		Artwork artwork = artworkRepository.findArtworkByArtworkID(artworkId);
		
		if(room == null) { // Check if room exists in system
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Room not found");
		}
		if(artwork == null) { // Check if artwork exists in system
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Artwork not found");
		}
		if(artwork.getRoom().getRoomID() == roomId) { // Check if artwork is already in the room we are trying to move it to
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Artwork already in given room");
		}
		if(artwork instanceof LoanableArtwork) { // Check if artwork is loanable
			LoanableArtwork loanableArtwork = (LoanableArtwork) artwork;
			if(!loanableArtwork.getIsAvailable()) { // Check if item is currently on loan
				throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Artwork is currently on loan; cannot be moved");
			}
		}
		if(room.getNumberOfArtwork() + 1 > room.getCapacity()) { // Check if room is full
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Room does not fit more artwork");
		}
		
		artwork.setRoom(room);
		artwork = artworkRepository.save(artwork);
	}
	
	@Transactional
	public void setArtworkLoanStatus(int artworkId, boolean setAvailable) {
		// FREQ3
		LoanableArtwork artwork = (LoanableArtwork) artworkRepository.findArtworkByArtworkID(artworkId);
		
		if(artwork == null) { // Check if artwork exists in system
			throw new MuseumSystemException(HttpStatus.NOT_FOUND, "Artwork not found");
		}
		if(artwork.getIsAvailable() == setAvailable) { // Check if current loan status of item is the same as the wanted status
			throw new MuseumSystemException(HttpStatus.BAD_REQUEST, "Artwork is already set to wanted status");
		}
		artwork.setIsAvailable(setAvailable);
	}
}
