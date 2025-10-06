package com.hotelmanagement.reservation_review_service.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Reservation",
        description = "Schema to hold Reservation information"
)
public class ReservationDto {

	@NotEmpty(message = "Guest Name can not be a null or empty")
    @Schema(
            description = "Name of Guest", example = "Krish Mahorkar"
    )
    private String guestName;
    
	@NotEmpty(message = "Guest Email can not be a null or empty")
	@Email(message = "Guest Email should be a valid email")
    @Schema(
            description = "Email of Guest", example = "krish@gmail.com"
    )
	private String guestEmail;

    @NotEmpty(message = "Guest Phone Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Guest Phone Number must be 10 digits")
    @Schema(
            description = "Phone Number of guest", example = "3454433243"
    )
	private String guestPhone;

    @NotNull(message = "CheckInDate can not be a null or empty")
    @Schema(
            description = "CheckInDate of Guest", example = "2025-02-11"
    )
	private LocalDate checkInDate;

    @NotNull(message = "CheckOutDate can not be a null or empty")
    @Schema(
            description = "CheckOutDate of Guest", example = "2025-02-19"
    )
	private LocalDate checkOutDate;

    @NotNull(message = "Room Id can not be a null or empty")
    @Schema(
            description = "Room Id of Guest", example = "1"
    )
	private Long roomId;
}
