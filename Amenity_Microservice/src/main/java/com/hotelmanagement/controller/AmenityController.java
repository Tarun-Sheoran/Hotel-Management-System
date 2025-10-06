package com.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagement.dto.AmenityDto;
import com.hotelmanagement.service.AmenityService;

import static com.hotelmanagement.constants.AmenityConstants.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/amenity")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @PostMapping("/post")
    public ResponseEntity<Map<String, String>> addAmenity(@RequestBody AmenityDto dto) {
        amenityService.addAmenity(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("code", STATUS_201, "message", MESSAGE_201));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAmenities() {
        List<AmenityDto> amenities = amenityService.getAllAmenities();
        if (amenities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("code", STATUS_404, "message", "Amenity list is empty"));
        }
        return ResponseEntity.ok(amenities);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getAmenityById(@PathVariable Long id) {
        AmenityDto amenity = amenityService.getAmenityById(id);
        return ResponseEntity.ok(amenity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateAmenity(@PathVariable Long id, @RequestBody AmenityDto dto) {
        amenityService.updateAmenity(id, dto);
        return ResponseEntity.ok(Map.of("code", STATUS_200, "message", "Amenity updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.ok(Map.of("code", STATUS_200, "message", "Amenity deleted successfully"));
    }
}
