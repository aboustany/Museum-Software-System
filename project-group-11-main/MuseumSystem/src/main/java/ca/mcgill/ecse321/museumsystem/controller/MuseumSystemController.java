package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.museumsystem.dto.EmployeeDto;
import ca.mcgill.ecse321.museumsystem.dto.MuseumSystemDTO;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;
import ca.mcgill.ecse321.museumsystem.service.EmployeeService;
import ca.mcgill.ecse321.museumsystem.service.MuseumSystemService;

/**
 * 
 * @author Karim
 * Controller for Employee class.
 *
 */
@CrossOrigin
@RestController
public class MuseumSystemController {
	
	@Autowired
	MuseumSystemService museumSystemService;
	
	@GetMapping("/museumSystem/{id}")
	public ResponseEntity<MuseumSystemDTO> getMuseumSystemByID(@PathVariable int id) {
		MuseumSystem museum = museumSystemService.getMuseumSystemByID(id);
		return new ResponseEntity<MuseumSystemDTO>(new MuseumSystemDTO(museum), HttpStatus.OK);
	}
	
	
	@PostMapping("/museumSystem")
	public ResponseEntity<MuseumSystemDTO> createMuseumSystem(@RequestBody MuseumSystem museum){
		museum = museumSystemService.createMuseumSystem(museum);
		return new ResponseEntity<MuseumSystemDTO>(new MuseumSystemDTO(museum), HttpStatus.CREATED);
	}

}
