package ca.mcgill.ecse321.museumsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//Author @Mehdi Ammor

@ControllerAdvice
public class MuseumSystemExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MuseumSystemException.class)
	public ResponseEntity<String> handleMuseumSystemException(MuseumSystemException ex) {
		return new ResponseEntity<String>(ex.getMessage(), ex.getStatus());
	}
	
}