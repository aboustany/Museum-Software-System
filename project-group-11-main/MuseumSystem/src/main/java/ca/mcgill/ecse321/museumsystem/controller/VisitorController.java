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

import ca.mcgill.ecse321.museumsystem.dto.MultipleVisitorResponsDTo;
import ca.mcgill.ecse321.museumsystem.dto.VisitorDto;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.service.VisitorService;

/**
 * 
 * @author Mehdi
 * Controller for Visitor class.
 */
@CrossOrigin
@RestController
public class VisitorController {
	
	@Autowired
	VisitorService visitorService;
	
	@GetMapping("/visitor/{id}")
	public ResponseEntity<Visitor> getVisitorById(@PathVariable int id) {
		Visitor visitor = visitorService.getVisitorByID(id);
		return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
	}

	@GetMapping("/visitor")
	public ResponseEntity<MultipleVisitorResponsDTo> getAllVisitors() {
		Iterable<Visitor> visitors = visitorService.getAllVisitors();
		MultipleVisitorResponsDTo response = new MultipleVisitorResponsDTo(visitors);
		return new ResponseEntity<MultipleVisitorResponsDTo>(response, HttpStatus.OK);
	}

	
	@PostMapping(value="/visitor", consumes = {"application/json"})
	public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor){
		visitor = visitorService.createVisitor(visitor);
		return new ResponseEntity<Visitor>(visitor, HttpStatus.CREATED);
	}

	@DeleteMapping("/visitor/{id}")
	public ResponseEntity<String> deleteVisitorById(@PathVariable int id) {
		Visitor visitor = visitorService.getVisitorByID(id);
		visitorService.deleteVisitor(visitor);
		return new ResponseEntity<String>("Visitor Deleted Successfully", HttpStatus.OK);
	}
	
}
