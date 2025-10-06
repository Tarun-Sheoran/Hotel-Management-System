package com.hotelmangement.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data@Setter@Getter@AllArgsConstructor@NoArgsConstructor
@Table(name = "roomtype")
public class RoomTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_type_id")
	private int roomTypeId;
	
	@Column(name="type_name",nullable = false, length = 255)
	private String type_name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="max_occupancy")
	private int max_occupancy;
	
	@Column(name="price_per_night")
	private double price_per_night;
	

	@OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;

}