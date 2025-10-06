package com.hotelmangement.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmangement.demo.dto.RoomDto;
import com.hotelmangement.demo.service.RoomServiceInterface;

@RestController
@RequestMapping("/api/room")
public class RoomController {
	@Autowired
	RoomServiceInterface roomServiceInterface;
	@GetMapping("/all")
	public List<RoomDto> getAllRooms(){
		return roomServiceInterface.getAllRooms();
	}
	@GetMapping("/{room_id}")
	public RoomDto getRoomById(@PathVariable int room_id) {
		return roomServiceInterface.getRoomById(room_id);
	}
	@GetMapping("/available/{roomTypeId}")
	public List<RoomDto> getRoomByRoomTypeId(@PathVariable int roomTypeId) {
		return roomServiceInterface.getRoomByRoomTypeId(roomTypeId);
	}
	@PostMapping("/post")
	public String createRoom(@RequestBody RoomDto roomDto) {
		return roomServiceInterface.createRoom(roomDto);
	}
	@PutMapping("/update/{roomId}")
	public String updateRoom(@PathVariable int roomId,@RequestBody RoomDto roomDto) {
		return roomServiceInterface.updateRoom(roomId,roomDto);
	}
}
