package com.hotelmangement.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelmangement.demo.dto.RoomDto;
@Service
public interface RoomServiceInterface {

	List<RoomDto> getAllRooms();
	RoomDto getRoomById(int room_id);
	List<RoomDto> getRoomByRoomTypeId(int roomTypeId);
	String createRoom(RoomDto roomDto);
	String updateRoom(int roomId, RoomDto roomDto);
}