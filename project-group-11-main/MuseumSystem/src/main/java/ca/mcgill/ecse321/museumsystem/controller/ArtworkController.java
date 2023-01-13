package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.museumsystem.dto.ArtworkDto;
import ca.mcgill.ecse321.museumsystem.dto.LoanableArtworkDto;
import ca.mcgill.ecse321.museumsystem.dto.MultipleArtworkResponseDto;
import ca.mcgill.ecse321.museumsystem.dto.MultipleEmployeeResponseDto;
import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.service.ArtworkService;

/**
 * 
 * @author Anthony Boustany
 * 
 * Artwork Controller class.
 *
 */
@CrossOrigin
@RestController
public class ArtworkController {

    @Autowired
    ArtworkService artworkService;

    @GetMapping("/artwork/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable int artworkId){
        Artwork artwork = artworkService.getArtworkById(artworkId);
        return new ResponseEntity<Artwork>(artwork, HttpStatus.OK);
    }
    
    @GetMapping("/artwork")
	public ResponseEntity<MultipleArtworkResponseDto> getAllEmployees() {
		Iterable<Artwork> artworks = artworkService.getAllArtworks();
		MultipleArtworkResponseDto response = new MultipleArtworkResponseDto(artworks);
		return new ResponseEntity<MultipleArtworkResponseDto>(response, HttpStatus.OK);
	}

    @PostMapping(value="/artwork", consumes = {"application/json"})
    public ResponseEntity<Artwork> createArtwork(@RequestBody Artwork artwork){
        artwork = artworkService.createArtwork(artwork);
        return new ResponseEntity<Artwork>(artwork, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/artwork/{id}")
    public ResponseEntity<String> deleteArtworkById(@PathVariable int artworkId){
        Artwork artwork = artworkService.getArtworkById(artworkId);
        artworkService.deleteArtworkById(artworkId);
        return new ResponseEntity<String>("Artwork Deleted Successfully", HttpStatus.OK);
    }
    

}