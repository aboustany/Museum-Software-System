package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.museumsystem.model.MuseumSystem;

public interface EntryPassRepository extends CrudRepository<EntryPass, String>{
	EntryPass findEntryPassByPassID(int passID);
}
