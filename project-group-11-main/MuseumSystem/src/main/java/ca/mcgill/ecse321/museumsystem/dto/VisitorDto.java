package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.Visitor;


/**
 * 
 * @author Mehdi Ammor
 * Data transfer object for Visitor class.
 * @param int id
 * @param String username
 *
 */
public class VisitorDto {
	
	private int id;
	
	private String username;
	
	private String password;
	
	public VisitorDto(Visitor visitor) {
		if(visitor != null) {
			this.id = visitor.getAccountID();
			this.username = visitor.getUserName();
			this.password = visitor.getPassword();
		}
		
	}

	public int getId() {
		return this.id;
	}
	
	public String getUserName() {
		return this.username;
	}

}
