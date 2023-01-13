package ca.mcgill.ecse321.museumsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Anthony Boustany
 * 
 * Artwork Exception class.
 *
 */

@SuppressWarnings("serial")
public class ArtworkException extends RuntimeException {

    private HttpStatus status;

    public ArtworkException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }

}