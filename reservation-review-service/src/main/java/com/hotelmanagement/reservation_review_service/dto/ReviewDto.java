package com.hotelmanagement.reservation_review_service.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Schema(
    name = "Review",
    description = "Schema to hold Review information"
)
public class ReviewDto {

    @NotNull(message = "Reservation ID cannot be null")
    @Schema(description = "ID of the reservation being reviewed", example = "101")
    private Long reservationId;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Schema(description = "Rating given by the guest (1 to 5)", example = "4")
    private Integer rating;

    @Schema(description = "Comment provided by the guest", example = "Great stay and service!")
    private String comment;

    @NotNull(message = "Review date cannot be null")
    @Schema(description = "Date when the review was submitted", example = "2025-02-20")
    private LocalDate reviewDate;
}