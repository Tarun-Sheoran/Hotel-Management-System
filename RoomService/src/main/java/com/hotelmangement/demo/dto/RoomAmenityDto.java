package com.hotelmangement.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class RoomAmenityDto {
	private int room_id;
	private int amenity_id;
}