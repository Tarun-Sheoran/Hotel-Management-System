package com.hotelmangement.demo.mapper;

import com.hotelmangement.demo.dto.RoomDto;
import com.hotelmangement.demo.entity.RoomEntity;

public class RoomMapper {
	public static RoomEntity mapToRoomEntity (RoomEntity roomEntity, RoomDto roomDto) {
		roomEntity.set_available(roomDto.is_available());
		roomEntity.setRoom_number(roomDto.getRoom_number());
		return roomEntity;
	}
	public static RoomDto mapToRoomDto(RoomEntity roomEntity, RoomDto roomDto) {
		roomDto.set_available(roomEntity.is_available());
		roomDto.setRoom_number(roomEntity.getRoom_number());
		roomDto.setRoom_type_id(roomEntity.getRoomType().getRoomTypeId());
		return roomDto;
	}
}