package com.hotelmangement.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class RoomTypeDto {
	private String type_name;
	private String description;
	private int max_occupancy;
	private double price_per_night;
}
