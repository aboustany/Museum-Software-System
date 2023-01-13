package ca.mcgill.ecse321.museumsystem.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.museumsystem.dto.MultipleEmployeeResponseDto;
import ca.mcgill.ecse321.museumsystem.dto.MultipleRoomResponseDto;
import ca.mcgill.ecse321.museumsystem.dto.RoomDto;
import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.Room;
import ca.mcgill.ecse321.museumsystem.service.RoomService;

@CrossOrigin
@RestController
public class RoomController {
    @Autowired
	RoomService roomService;
	
	@GetMapping("/room/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable int id) {
		Room room = roomService.getRoomById(id);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	

	@PostMapping("/room")
	public ResponseEntity<Room> createRoom(@RequestBody Room request){
		request = roomService.createRoom(request);
		return new ResponseEntity<Room>(request, HttpStatus.CREATED);
	}
	
	@GetMapping("/room")
	public ResponseEntity<MultipleRoomResponseDto> getAllRooms() {
		Iterable<Room> rooms = roomService.getAllRooms();
		MultipleRoomResponseDto response = new MultipleRoomResponseDto(rooms);
		return new ResponseEntity<MultipleRoomResponseDto>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/room/{id}")
	public ResponseEntity<String> deleteRoomById(@PathVariable int id) {
		Room room = roomService.getRoomById(id);
		roomService.deleteRoom(room);
		return new ResponseEntity<String>("Room Deleted Successfully", HttpStatus.OK);
	}
}