package com.hotelmangement.demo.mapper;

import com.hotelmangement.demo.dto.RoomTypeDto;
import com.hotelmangement.demo.entity.RoomTypeEntity;

public class RoomTypeMapper {
	public static RoomTypeEntity mapToRoomTypeEntity (RoomTypeEntity roomTypeEntity, RoomTypeDto roomTypeDto) {
		roomTypeEntity.setDescription(roomTypeDto.getDescription());
		roomTypeEntity.setMax_occupancy(roomTypeDto.getMax_occupancy());
		roomTypeEntity.setPrice_per_night(roomTypeDto.getPrice_per_night());
		roomTypeEntity.setType_name(roomTypeDto.getType_name());
		return roomTypeEntity;
	}
	public static RoomTypeDto mapToRoomTypeDto (RoomTypeEntity roomTypeEntity, RoomTypeDto roomTypeDto) {
		roomTypeDto.setDescription(roomTypeEntity.getDescription());
		roomTypeDto.setMax_occupancy(roomTypeEntity.getMax_occupancy());
		roomTypeDto.setPrice_per_night(roomTypeEntity.getPrice_per_night());
		roomTypeDto.setType_name(roomTypeEntity.getType_name());
		return roomTypeDto;
	}
}