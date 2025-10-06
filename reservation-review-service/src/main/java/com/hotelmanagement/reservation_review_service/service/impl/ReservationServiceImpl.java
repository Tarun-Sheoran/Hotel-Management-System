package com.hotelmanagement.reservation_review_service.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.reservation_review_service.dto.ReservationDto;
import com.hotelmanagement.reservation_review_service.entity.Reservation;
import com.hotelmanagement.reservation_review_service.exception.ResourceNotFoundException;
import com.hotelmanagement.reservation_review_service.exception.ReservationAlreadyExistsException;
import com.hotelmanagement.reservation_review_service.repository.ReservationRepository;
import com.hotelmanagement.reservation_review_service.service.ReservationService;

/**
 * Implementation of the ReservationService interface.
 * Handles business logic related to reservation operations.
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Creates a new reservation if no duplicate exists.
     *
     * @param dto - ReservationDto containing reservation details
     * @return the created ReservationDto
     * @throws ReservationAlreadyExistsException if a reservation already exists for the same guest and check-in date
     */
    @Override
    public ReservationDto createReservation(ReservationDto dto) {
        List<Reservation> existingReservations = reservationRepository.findAll().stream()
                .filter(r -> r.getGuestEmail().equals(dto.getGuestEmail()) &&
                             r.getCheckInDate().equals(dto.getCheckInDate()))
                .collect(Collectors.toList());

        if (!existingReservations.isEmpty()) {
            throw new ReservationAlreadyExistsException("Reservation already exists for guest: " + dto.getGuestEmail());
        }

        Reservation reservation = mapToEntity(dto);
        Reservation saved = reservationRepository.save(reservation);
        return mapToDto(saved);
    }

    /**
     * Updates an existing reservation.
     *
     * @param reservationId - ID of the reservation to update
     * @param dto - ReservationDto containing updated details
     * @return the updated ReservationDto
     * @throws ResourceNotFoundException if the reservation is not found
     */
    @Override
    public ReservationDto updateReservation(Long reservationId, ReservationDto dto) {
        Reservation existing = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + reservationId));

        existing.setGuestName(dto.getGuestName());
        existing.setGuestEmail(dto.getGuestEmail());
        existing.setGuestPhone(dto.getGuestPhone());
        existing.setCheckInDate(dto.getCheckInDate());
        existing.setCheckOutDate(dto.getCheckOutDate());
        existing.setRoomId(dto.getRoomId());

        Reservation updated = reservationRepository.save(existing);
        return mapToDto(updated);
    }

    /**
     * Retrieves a reservation by its ID.
     *
     * @param reservationId - ID of the reservation
     * @return the corresponding ReservationDto
     * @throws ResourceNotFoundException if the reservation is not found
     */
    @Override
    public ReservationDto getReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + reservationId));
        return mapToDto(reservation);
    }

    /**
     * Retrieves all reservations.
     *
     * @return a list of ReservationDto objects
     * @throws ResourceNotFoundException if no reservations are found
     */
    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("Reservation list is empty");
        }
        return reservations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param reservationId - ID of the reservation to delete
     * @throws ResourceNotFoundException if the reservation is not found
     */
    @Override
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + reservationId));
        reservationRepository.delete(reservation);
    }

    /**
     * Retrieves reservations within a specific date range.
     *
     * @param startDate - start date of the range
     * @param endDate - end date of the range
     * @return a list of ReservationDto objects within the date range
     * @throws ResourceNotFoundException if no reservations are found in the range
     */
    @Override
    public List<ReservationDto> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationRepository.findByCheckInDateBetween(startDate, endDate);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("No reservations found between " + startDate + " and " + endDate);
        }
        return reservations.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Converts a Reservation entity to a ReservationDto.
     *
     * @param reservation - Reservation entity
     * @return ReservationDto
     */
    private ReservationDto mapToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setGuestName(reservation.getGuestName());
        dto.setGuestEmail(reservation.getGuestEmail());
        dto.setGuestPhone(reservation.getGuestPhone());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setRoomId(reservation.getRoomId());
        return dto;
    }

    /**
     * Converts a ReservationDto to a Reservation entity.
     *
     * @param dto - ReservationDto
     * @return Reservation entity
     */
    private Reservation mapToEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setGuestName(dto.getGuestName());
        reservation.setGuestEmail(dto.getGuestEmail());
        reservation.setGuestPhone(dto.getGuestPhone());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setRoomId(dto.getRoomId());
        return reservation;
    }
}