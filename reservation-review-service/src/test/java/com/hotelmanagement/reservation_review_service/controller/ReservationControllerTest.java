package com.hotelmanagement.reservation_review_service.controller;

import com.hotelmanagement.reservation_review_service.dto.ReservationDto;
import com.hotelmanagement.reservation_review_service.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReservationControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ReservationService reservationService;

	@InjectMocks
	private ReservationController reservationController;

	private ReservationDto sampleReservation;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

		sampleReservation = new ReservationDto();
		sampleReservation.setGuestName("Krish Mahorkar");
		sampleReservation.setGuestEmail("krish@gmail.com");
		sampleReservation.setGuestPhone("9876543210");
		sampleReservation.setCheckInDate(LocalDate.of(2025, 2, 11));
		sampleReservation.setCheckOutDate(LocalDate.of(2025, 2, 19));
		sampleReservation.setRoomId(1L);
	}

	@Test
	void createReservation_returnsCreated() throws Exception {
		when(reservationService.createReservation(any(ReservationDto.class))).thenReturn(sampleReservation);

		String json = """
				{
				    "guestName": "Krish Mahorkar",
				    "guestEmail": "krish@gmail.com",
				    "guestPhone": "9876543210",
				    "checkInDate": "2025-02-11",
				    "checkOutDate": "2025-02-19",
				    "roomId": 1
				}
				""";

		mockMvc.perform(post("/api/reservation/post").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	void getReservationById_returnsOk() throws Exception {
		when(reservationService.getReservationById(1L)).thenReturn(sampleReservation);

		mockMvc.perform(get("/api/reservation/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.guestEmail").value("krish@gmail.com"));
	}

	@Test
	void getAllReservations_returnsOk() throws Exception {
		when(reservationService.getAllReservations()).thenReturn(Collections.singletonList(sampleReservation));

		mockMvc.perform(get("/api/reservation/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].guestName").value("Krish Mahorkar"));
	}

	@Test
	void updateReservation_returnsOk() throws Exception {
		when(reservationService.updateReservation(eq(1L), any(ReservationDto.class))).thenReturn(sampleReservation);

		String json = """
				{
				    "guestName": "Krish Mahorkar",
				    "guestEmail": "krish@gmail.com",
				    "guestPhone": "9876543210",
				    "checkInDate": "2025-02-11",
				    "checkOutDate": "2025-02-19",
				    "roomId": 1
				}
				""";

		mockMvc.perform(put("/api/reservation/update/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	void deleteReservation_returnsOk() throws Exception {
		doNothing().when(reservationService).deleteReservation(1L);

		mockMvc.perform(delete("/api/reservation/1")).andExpect(status().isOk());
	}

	@Test
	void getReservationsByDateRange_returnsOk() throws Exception {
		when(reservationService.getReservationsByDateRange(any(), any()))
				.thenReturn(Collections.singletonList(sampleReservation));

		mockMvc.perform(
				get("/api/reservation/date-range").param("startDate", "2025-02-11").param("endDate", "2025-02-19"))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].guestEmail").value("krish@gmail.com"));
	}
}