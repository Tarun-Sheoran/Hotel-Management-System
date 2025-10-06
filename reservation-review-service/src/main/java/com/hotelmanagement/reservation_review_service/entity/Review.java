package com.hotelmanagement.reservation_review_service.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	@Column(name = "review_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	
//	@ManyToOne
//  @JoinColumn(name = "reservation_id", nullable = false)
//  private Reservation reservation;

	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "comment")
	private String comment;

	@Column(name = "review_date")
	private LocalDate reviewDate;

}
