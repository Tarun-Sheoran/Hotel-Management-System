package com.hotelmanagement.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(
    name = "Amenity",
    description = "Schema to hold Amenity information"
)
public class AmenityDto {

    @Schema(description = "Unique ID of the amenity", example = "1")
    private Long amenityId;

    @NotEmpty(message = "Amenity name cannot be null or empty")
    @Schema(description = "Name of the amenity", example = "Wi-Fi")
    private String name;

    @NotEmpty(message = "Description cannot be null or empty")
    @Schema(description = "Description of the amenity", example = "High-speed wireless internet")
    private String description;
}



