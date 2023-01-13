package ca.mcgill.ecse321.museumsystem.model;

import org.springframework.data.repository.CrudRepository;


public interface MuseumSystemRepository extends CrudRepository<MuseumSystem, String>{
	MuseumSystem findMuseumSystemByMuseumID(int museumID);
}
