package com.hotelmangement.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmangement.demo.dto.RoomDto;
import com.hotelmangement.demo.entity.RoomEntity;
import com.hotelmangement.demo.mapper.RoomMapper;
import com.hotelmangement.demo.repository.RoomRepository;
@Service
public class RoomServiceInterfaceImpl implements RoomServiceInterface{
	@Autowired
	RoomRepository roomRepository;
	@Override
	public List<RoomDto> getAllRooms() {
		List<RoomEntity> rooms =roomRepository.findAll();
		return rooms.stream().map(r -> RoomMapper.mapToRoomDto(r, new RoomDto())).collect(Collectors.toList());
	}
	@Override
	public RoomDto getRoomById(int roomId) {
		RoomEntity roomEntity = roomRepository.getByRoomId(roomId).orElse(null);
		RoomDto roomDto=RoomMapper.mapToRoomDto(roomEntity, new RoomDto());
		return roomDto;
	}
	@Override
	public List<RoomDto> getRoomByRoomTypeId(int roomTypeId) {
		List<RoomEntity> roomEntity = roomRepository.findByRoomType_RoomTypeId(roomTypeId);
		return roomEntity.stream().filter(RoomEntity::is_available).map(r -> RoomMapper.mapToRoomDto(r, new RoomDto())).collect(Collectors.toList());

	}
	@Override
	public String createRoom(RoomDto roomDto) {
		RoomEntity roomEntity= RoomMapper.mapToRoomEntity(new RoomEntity(), roomDto);
		if(roomEntity!=null) {
			roomRepository.save(roomEntity);
			return "New Room Added";
		}
		return "No New Rooms to add";
	}
	@Override
	public String updateRoom(int roomId, RoomDto roomDto) {
		RoomEntity oldRoomEntity= roomRepository.getByRoomId(roomId).orElse(null);
		if(oldRoomEntity!=null) {
			RoomEntity roomEntity = RoomMapper.mapToRoomEntity(new RoomEntity(), roomDto);
			oldRoomEntity.set_available(roomEntity.is_available());
			oldRoomEntity.setRoom_number(roomEntity.getRoom_number());
			oldRoomEntity.setRoomType(roomEntity.getRoomType());
			roomRepository.save(oldRoomEntity);
		}
		return "Room Updated";
	}
}