package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import ca.mcgill.ecse321.museumsystem.dto.OwnerDto;
import ca.mcgill.ecse321.museumsystem.model.Owner;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.service.OwnerService;

/**
 * 
 * @author Mehdi
 * Controller for Owner class.
 *
 */
@CrossOrigin
@RestController
public class OwnerController {
	
	@Autowired
	OwnerService ownerService;
	
	@GetMapping("/owner/{id}")
	public ResponseEntity<Owner> getOwnerByID(@PathVariable int id) {
		Owner owner = ownerService.getOwnerbyID(id);
		return new ResponseEntity<Owner>(owner, HttpStatus.OK);
	}
	
	@PostMapping("/owner")
	public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
		owner = ownerService.createOwner(owner);
		return new ResponseEntity<Owner>(owner, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/owner/{id}")
	public ResponseEntity<String> deleteOwnerById(@PathVariable int id) {
		Owner owner = ownerService.getOwnerbyID(id);
		ownerService.deleteOwner(owner);
		return new ResponseEntity<String>("Owner Deleted Successfully", HttpStatus.OK);
	}
}