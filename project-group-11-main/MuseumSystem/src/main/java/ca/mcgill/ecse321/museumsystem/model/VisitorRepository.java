package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.Account;

public interface VisitorRepository extends CrudRepository<Visitor, String>{
	Visitor findVisitorByAccountID(int accountID);
}
