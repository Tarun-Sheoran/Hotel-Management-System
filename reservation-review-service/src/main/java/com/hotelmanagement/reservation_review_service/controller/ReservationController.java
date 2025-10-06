package com.hotelmanagement.reservation_review_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagement.reservation_review_service.dto.ReservationDto;
import com.hotelmanagement.reservation_review_service.dto.ResponseDto;
import com.hotelmanagement.reservation_review_service.service.ReservationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/reservation")
@Tag(
    name = "Reservation Management REST APIs",
    description = "CRUD REST APIs to create, update, fetch, delete and filter reservation details"
)
public class ReservationController {

   @Autowired
   private ReservationService reservationService;

   @Operation(
        summary = "Create Reservation",
        description = "Creates a new reservation for a guest"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Reservation created successfully"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> createReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.createReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("POSTSUCCESS", "Reservation added successfully"));
    }

    @Operation(
        summary = "Update Reservation",
        description = "Updates an existing reservation by reservation ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reservation updated successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/update/{reservationId}")
    public ResponseEntity<ResponseDto> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody ReservationDto reservationDto) {
        reservationService.updateReservation(reservationId, reservationDto);
        return ResponseEntity.ok(new ResponseDto("UPDATESUCCESS", "Reservation updated successfully"));
    }

    @Operation(
        summary = "Get Reservation by ID",
        description = "Fetches reservation details using reservation ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reservation fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long reservationId) {
        ReservationDto reservation = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @Operation(
        summary = "Get All Reservations",
        description = "Fetches all reservations from the system"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reservations fetched successfully"),
        @ApiResponse(responseCode = "404", description = "No reservations found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @Operation(
        summary = "Delete Reservation",
        description = "Deletes a reservation by reservation ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reservation deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ResponseDto> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(new ResponseDto("DELETESUCCESS", "Reservation deleted successfully"));
    }

    @Operation(
        summary = "Get Reservations by Date Range",
        description = "Fetches reservations between the given start and end dates"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reservations fetched successfully"),
        @ApiResponse(responseCode = "404", description = "No reservations found in the given date range")
    })
    @GetMapping("/date-range")
    public ResponseEntity<List<ReservationDto>> getReservationsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ReservationDto> reservations = reservationService.getReservationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }
}