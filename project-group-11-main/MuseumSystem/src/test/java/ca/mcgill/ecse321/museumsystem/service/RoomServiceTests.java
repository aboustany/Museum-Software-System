package ca.mcgill.ecse321.museumsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.model.RoomRepository;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTests {

    @Mock
    private RoomRepository roomRepository;
    
    @InjectMocks
    private RoomService roomService;


    ArrayList<Room> allRooms;

    /**
     * @author: Elsa Chelala
     * createRooms creates the rooms needed by all the tests
     */
    @BeforeEach
    public void createRooms() {
        allRooms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Room smallRoom = new Room();
            smallRoom.setCapacity(200);
            allRooms.add(smallRoom);
            Room bigRoom = new Room();
            bigRoom.setCapacity(300);
            allRooms.add(bigRoom);
        }
        Room storage = new Room();
        storage.setCapacity(Integer.MAX_VALUE);
        allRooms.add(storage);
    }

    /**
     * deleteRooms clears the rooms at the end of each testing phase
     */
    @AfterEach
    public void deleteRooms(){
        allRooms.clear();

    }

    /**
     * testGetRoomById tests the system's capacity to retrieve one room
     */
    @Test
    public void testGetRoomById() {
        // Create mock
        when(roomRepository.findRoomByRoomID(any(int.class))).thenAnswer((InvocationOnMock invocation) -> allRooms.get(0));
        
        Room room = roomService.getRoomById(0);
        
        assertEquals(allRooms.get(0).getRoomID(), room.getRoomID());
        assertEquals(allRooms.get(0).getNumberOfArtwork(), room.getNumberOfArtwork());
        assertEquals(allRooms.get(0).getCapacity(), room.getCapacity());
        
        verify(roomRepository, times (1)).findRoomByRoomID(0);
    }


    /**
     * testRooms tests the system's ability to get all rooms. 
     */
    @Test
    public void testGetAllRooms() {
        when(roomRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> allRooms);
        ArrayList<Room> rooms = roomService.getAllRooms();
       
        assertEquals(allRooms, rooms);

        verify(roomRepository, times(1)).findAll();
    }

    /**
     * testCreateRooms tests the system's ability to create 11 rooms. 
     */
    @Test
    public void testCreateRooms() {
        for (int i = 0; i < 11; i++) {
            Room room = allRooms.get(i);
            // Create mocks
            lenient().when(roomRepository.save(room)).thenAnswer((InvocationOnMock invocation) -> room);
        }
        ArrayList<Room> rooms = roomService.createRooms();
        
        // Verify 11 rooms were created and saved
        assertEquals(allRooms.size(), rooms.size());
        verify(roomRepository, times(11)).save(any(Room.class));
    }

}
