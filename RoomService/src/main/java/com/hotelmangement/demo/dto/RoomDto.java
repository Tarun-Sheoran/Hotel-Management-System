package com.hotelmangement.demo.dto;

import com.hotelmangement.demo.entity.RoomTypeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class RoomDto {
	private int room_number;
	private int room_type_id;
	private boolean is_available;
}