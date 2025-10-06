package com.hotelmanagement.reservation_review_service.service;

import com.hotelmanagement.reservation_review_service.dto.ReservationDto;
import com.hotelmanagement.reservation_review_service.entity.Reservation;
import com.hotelmanagement.reservation_review_service.exception.ResourceNotFoundException;
import com.hotelmanagement.reservation_review_service.exception.ReservationAlreadyExistsException;
import com.hotelmanagement.reservation_review_service.repository.ReservationRepository;
import com.hotelmanagement.reservation_review_service.service.impl.ReservationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationServiceImpl reservationService;

	private ReservationDto reservationDto;
	private Reservation reservation;

	@BeforeEach
	void setUp() {
		reservationDto = new ReservationDto();
		reservationDto.setGuestName("Krish Mahorkar");
		reservationDto.setGuestEmail("krish@gmail.com");
		reservationDto.setGuestPhone("9876543210");
		reservationDto.setCheckInDate(LocalDate.of(2025, 2, 11));
		reservationDto.setCheckOutDate(LocalDate.of(2025, 2, 19));
		reservationDto.setRoomId(1L);

		reservation = new Reservation();
		reservation.setReservationId(1L);
		reservation.setGuestName("Krish Mahorkar");
		reservation.setGuestEmail("krish@gmail.com");
		reservation.setGuestPhone("9876543210");
		reservation.setCheckInDate(LocalDate.of(2025, 2, 11));
		reservation.setCheckOutDate(LocalDate.of(2025, 2, 19));
		reservation.setRoomId(1L);
	}

	@Test
	void createReservation_success() {
		when(reservationRepository.findAll()).thenReturn(Collections.emptyList());
		when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

		ReservationDto result = reservationService.createReservation(reservationDto);

		assertNotNull(result);
		assertEquals("Krish Mahorkar", result.getGuestName());
		verify(reservationRepository).save(any(Reservation.class));
	}

	@Test
	void createReservation_duplicate_throwsException() {
		when(reservationRepository.findAll()).thenReturn(List.of(reservation));

		assertThrows(ReservationAlreadyExistsException.class,
				() -> reservationService.createReservation(reservationDto));
		verify(reservationRepository, never()).save(any());
	}

	@Test
	void updateReservation_success() {
		when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
		when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

		ReservationDto updatedDto = new ReservationDto();
		updatedDto.setGuestName("Krish M.");
		updatedDto.setGuestEmail("krish@gmail.com");
		updatedDto.setGuestPhone("9876543210");
		updatedDto.setCheckInDate(LocalDate.of(2025, 2, 12));
		updatedDto.setCheckOutDate(LocalDate.of(2025, 2, 20));
		updatedDto.setRoomId(2L);

		ReservationDto result = reservationService.updateReservation(1L, updatedDto);

		assertEquals("Krish M.", result.getGuestName());
		assertEquals(2L, result.getRoomId());
	}

	@Test
	void updateReservation_notFound_throwsException() {
		when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> reservationService.updateReservation(1L, reservationDto));
	}

	@Test
	void getReservationById_success() {
		when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

		ReservationDto result = reservationService.getReservationById(1L);

		assertEquals("Krish Mahorkar", result.getGuestName());
	}

	@Test
	void getReservationById_notFound_throwsException() {
		when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> reservationService.getReservationById(1L));
	}

	@Test
	void getAllReservations_success() {
		when(reservationRepository.findAll()).thenReturn(List.of(reservation));

		List<ReservationDto> result = reservationService.getAllReservations();

		assertEquals(1, result.size());
	}

	@Test
	void getAllReservations_empty_throwsException() {
		when(reservationRepository.findAll()).thenReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> reservationService.getAllReservations());
	}

	@Test
	void deleteReservation_success() {
		when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
		doNothing().when(reservationRepository).delete(reservation);

		assertDoesNotThrow(() -> reservationService.deleteReservation(1L));
		verify(reservationRepository).delete(reservation);
	}

	@Test
	void deleteReservation_notFound_throwsException() {
		when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> reservationService.deleteReservation(1L));
	}

	@Test
	void getReservationsByDateRange_success() {
		when(reservationRepository.findByCheckInDateBetween(any(), any())).thenReturn(List.of(reservation));

		List<ReservationDto> result = reservationService.getReservationsByDateRange(LocalDate.of(2025, 2, 10),
				LocalDate.of(2025, 2, 20));

		assertEquals(1, result.size());
	}

	@Test
	void getReservationsByDateRange_empty_throwsException() {
		when(reservationRepository.findByCheckInDateBetween(any(), any())).thenReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> reservationService
				.getReservationsByDateRange(LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 20)));
	}
}