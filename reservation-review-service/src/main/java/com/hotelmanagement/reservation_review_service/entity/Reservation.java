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
public class Reservation {

	@Column(name = "reservation_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;

	@Column(name = "guest_name")
	private String guestName;

	@Column(name = "guest_email")
	private String guestEmail;

	@Column(name = "guest_phone")
	private String guestPhone;

	@Column(name = "check_in_date")
	private LocalDate checkInDate;

	@Column(name = "check_out_date")
	private LocalDate checkOutDate;

	@Column(name = "room_id")
	private Long roomId;

}
