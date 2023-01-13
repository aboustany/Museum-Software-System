package ca.mcgill.ecse321.museumsystem.dto;

import java.util.ArrayList;

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.Employee;

public class MultipleArtworkResponseDto {
	
	private Iterable<Artwork> artworks;
	
	public MultipleArtworkResponseDto(Iterable<Artwork> artworks) {
		ArrayList<Artwork> artwork = new ArrayList<Artwork>();
		for (Artwork a : artworks) {
			artwork.add(a);
		}
		this.artworks = artwork;
	}
	
	public Iterable<Artwork> getArtworks() {
		return this.artworks;
	}

}
