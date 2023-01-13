package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.Account;

public interface OwnerRepository extends CrudRepository<Owner, String>{
	Owner findOwnerByAccountID(int accountID);
}
