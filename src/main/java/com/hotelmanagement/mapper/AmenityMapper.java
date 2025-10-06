package com.hotelmanagement.mapper;


import com.hotelmanagement.dto.AmenityDto;
import com.hotelmanagement.entity.Amenity;

public class AmenityMapper {

    public static AmenityDto mapToAmenityDto(Amenity amenity, AmenityDto amenityDto) {
        amenityDto.setAmenityId(amenity.getAmenityId());
        amenityDto.setName(amenity.getName());
        amenityDto.setDescription(amenity.getDescription());
        return amenityDto;
    }

    public static Amenity mapToAmenity(AmenityDto amenityDto, Amenity amenity) {
        amenity.setAmenityId(amenityDto.getAmenityId());
        amenity.setName(amenityDto.getName());
        amenity.setDescription(amenityDto.getDescription());
        return amenity;
    }
}


