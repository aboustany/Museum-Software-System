package ca.mcgill.ecse321.museumsystem.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Anthony Boustany
 * 
 * EntryPass Exception class.
 *
 */

@SuppressWarnings("serial")
public class EntryPassException extends RuntimeException {

    private HttpStatus status;

    public EntryPassException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }

}