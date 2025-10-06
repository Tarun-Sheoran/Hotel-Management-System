package com.hotelmangement.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Table(name="room")
public class RoomEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="room_number")
	private int room_number;
	
	@ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
	private RoomTypeEntity roomType;
	
	@Column(name="is_available")
	private boolean is_available;
}