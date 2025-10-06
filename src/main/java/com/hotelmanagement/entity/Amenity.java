package com.hotelmanagement.entity;
import jakarta.persistence.*;
import lombok.*;



	@Entity
	@Table(name = "amenity")
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Amenity {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "amenity_id")
	    private Long amenityId;

	    @Column(nullable = false, unique = true)
	    private String name;

	    @Column(columnDefinition = "TEXT")
	    private String description;
	}



	

