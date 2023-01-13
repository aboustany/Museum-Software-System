package ca.mcgill.ecse321.museumsystem.exception;

import org.springframework.http.HttpStatus;


//Author @Mehdi Ammor

@SuppressWarnings("Serial")
public class MuseumSystemException extends RuntimeException {

    // this exception has an HTTP status in addition to a method
    private HttpStatus status; 

    public MuseumSystemException (HttpStatus status, String message) { 
        super(message); 
        this.status = status; 
        
    }

    public HttpStatus getStatus() {
        return status;
    }
    
    
}