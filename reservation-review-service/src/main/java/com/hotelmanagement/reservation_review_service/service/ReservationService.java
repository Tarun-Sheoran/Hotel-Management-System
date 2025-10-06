package com.hotelmanagement.reservation_review_service.service;

import java.time.LocalDate;
import java.util.List;

import com.hotelmanagement.reservation_review_service.dto.ReservationDto;

/**
 * Service interface for managing reservations.
 */
public interface ReservationService {

    /**
     * Creates a new reservation.
     *
     * @param reservationDto - DTO containing reservation details
     * @return the created ReservationDto
     */
    ReservationDto createReservation(ReservationDto reservationDto);

    /**
     * Updates an existing reservation.
     *
     * @param reservationId - ID of the reservation to update
     * @param reservationDto - DTO containing updated reservation details
     * @return the updated ReservationDto
     */
    ReservationDto updateReservation(Long reservationId, ReservationDto reservationDto);

    /**
     * Retrieves a reservation by its ID.
     *
     * @param reservationId - ID of the reservation
     * @return the ReservationDto corresponding to the given ID
     */
    ReservationDto getReservationById(Long reservationId);

    /**
     * Retrieves all reservations.
     *
     * @return a list of all ReservationDto objects
     */
    List<ReservationDto> getAllReservations();

    /**
     * Deletes a reservation by its ID.
     *
     * @param reservationId - ID of the reservation to delete
     */
    void deleteReservation(Long reservationId);

    /**
     * Retrieves reservations within a specific date range.
     *
     * @param startDate - start date of the range
     * @param endDate - end date of the range
     * @return a list of ReservationDto objects within the date range
     */
    List<ReservationDto> getReservationsByDateRange(LocalDate startDate, LocalDate endDate);
}