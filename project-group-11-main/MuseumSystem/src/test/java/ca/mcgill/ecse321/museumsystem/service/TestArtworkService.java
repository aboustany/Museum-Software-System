package ca.mcgill.ecse321.museumsystem.service;

import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.model.Artwork;
import ca.mcgill.ecse321.museumsystem.model.LoanableArtwork;
import ca.mcgill.ecse321.museumsystem.model.ArtworkRepository;

import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;

/**
 * 
 * @author Anthony Boustany
 * 
 * Artwork Service Tests.
 *
 */

@ExtendWith(MockitoExtension.class)
public class TestArtworkService {
    
    @Mock
    ArtworkRepository artworkRepository;

    @Mock
    RoomRepository roomRepository;

    @InjectMocks
    private ArtworkService artworkService;

    private static final int ARTWORK_KEY = 333;
    private static final int ROOM_ID = 7;

    @BeforeEach
    public void setMockOutput(){
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(artworkRepository.save(any(Artwork.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(artworkRepository.findArtworkByArtworkID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ARTWORK_KEY)){
                Artwork artwork = new Artwork();
                Room room = new Room();

				artwork.setArtworkID(ARTWORK_KEY);
                artwork.setRoom(room);

				return artwork;		
            }	
            else return null;
		});
    }

    @AfterEach
    public void deleteArtworks(){
        artworkRepository.deleteAll();
    }

    @Test
    public void testCreateArtwork(){

        Artwork MonaLisa = new Artwork();
        Room room = new Room();
        
        MonaLisa.setArtworkID(ARTWORK_KEY);
        MonaLisa.setRoom(room);
        
        Artwork testArtwork = artworkService.createArtwork(MonaLisa);

        assertNotNull(testArtwork);
        assertEquals(testArtwork.getArtworkID(), MonaLisa.getArtworkID());
       
    }

    @Test
    public void testCreateArtworkNull(){

        Artwork artwork = null;
		artwork = artworkService.createArtwork(artwork);
		assertNull(artwork);
        
    }


    //works for Artwork and LoanableArtwork
    @Test
    public void testGetArtworkById(){

        Artwork artwork = new Artwork();
        artwork.setArtworkID(ARTWORK_KEY);
        
        Room tmpRoom = new Room();
        artwork.setRoom(tmpRoom);

        artwork = artworkService.createArtwork(artwork);

        try{
            Artwork testArtwork = artworkService.getArtworkById(ARTWORK_KEY);
            assertEquals(artwork.getArtworkID(), testArtwork.getArtworkID());
        }
        catch(Exception e){
            System.out.print("Artwork not found");
            fail();
        }
   
    }

    @Test
    public void testGetNullArtworkById(){
        int artworkId = 999;
		Artwork artwork = artworkService.getArtworkById(artworkId);
		assertNull(artwork);
    }

    @Test
    public void testCreateLoanabaleArtwork(){

        LoanableArtwork Scimitar = new LoanableArtwork();
        Room room = new Room();
        
        Scimitar.setArtworkID(ARTWORK_KEY);
        Scimitar.setRoom(room);
        
        Artwork testLoanableArtwork = artworkService.createLoanableArtwork(Scimitar);

        assertNotNull(testLoanableArtwork);
        assertEquals(testLoanableArtwork.getArtworkID(), Scimitar.getArtworkID());
       
    }

    @Test
    public void testCreateLoanableArtworkNull(){

        LoanableArtwork loanableArtwork = null;
		loanableArtwork = artworkService.createLoanableArtwork(loanableArtwork);
		assertNull(loanableArtwork);
        
    }



}