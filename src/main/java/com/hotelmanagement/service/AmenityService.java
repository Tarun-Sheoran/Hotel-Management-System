package com.hotelmanagement.service;


import java.util.List;

import com.hotelmanagement.dto.AmenityDto;

public interface AmenityService {
	
    AmenityDto addAmenity(AmenityDto dto);
    List<AmenityDto> getAllAmenities();
    AmenityDto getAmenityById(Long id);
    AmenityDto updateAmenity(Long id, AmenityDto dto);
    void deleteAmenity(Long id);
}
