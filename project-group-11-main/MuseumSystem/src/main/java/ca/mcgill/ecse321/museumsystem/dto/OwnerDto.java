package ca.mcgill.ecse321.museumsystem.dto;
import ca.mcgill.ecse321.museumsystem.model.Owner;


/**
 * 
 * @author Mehdi Ammor
 * Data transfer object for Owner class.
 * @param int id
 * @param String username
 *
 */
public class OwnerDto {
	
	private int id;
	
	private String username;
	private String password;
	
	public OwnerDto(Owner owner) {
		if(owner != null) {
			this.id = owner.getAccountID();
			this.username = owner.getUserName();
		}
	}

	public int getId() {
		return this.id;
	}
	
	public String getUserName() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setUsername(String ausername) {
		username = ausername;
	}

	public void setPassword(String apassword) {
		password = apassword;
	}


}
