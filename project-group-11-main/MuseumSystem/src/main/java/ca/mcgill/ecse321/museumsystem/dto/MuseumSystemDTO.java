package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;


/**
 * 
 * @author Karim
 * Data transfer object for Employee class.
 * @param int id
 * @param String username
 *
 */
public class MuseumSystemDTO{
	
	private int id;
	
	public MuseumSystemDTO(MuseumSystem museum) {
		if(museum != null) {
			this.id = museum.getMuseumID();
		
		}
		
	}
	
	public int getId() {
		return this.id;
	}
	

}