package com.hotelmangement.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Table(name="roomamenity")
public class RoomAmenityEntity {
	@Column(name="room_id")
	private int room_id;
	@Column(name="amenity_id")
	private int amenity_id;
}