package com.hotelmanagement.sevice.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.hotelmanagement.dto.AmenityDto;
import com.hotelmanagement.entity.Amenity;
import com.hotelmanagement.exception.ResourceNotFoundException;
import com.hotelmanagement.mapper.AmenityMapper;
import com.hotelmanagement.repository.AmenityRepository;
import com.hotelmanagement.service.AmenityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    @Override
    public AmenityDto addAmenity(AmenityDto dto) {
        if (amenityRepository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Amenity already exists");
        }
        Amenity amenity = AmenityMapper.mapToAmenity(dto, new Amenity());
        Amenity savedAmenity = amenityRepository.save(amenity);
        return AmenityMapper.mapToAmenityDto(savedAmenity, new AmenityDto());
    }

    @Override
    public List<AmenityDto> getAllAmenities() {
        return amenityRepository.findAll().stream()
                .map(amenity -> AmenityMapper.mapToAmenityDto(amenity, new AmenityDto()))
                .collect(Collectors.toList());
    }

    @Override
    public AmenityDto getAmenityById(Long id) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found"));
        return AmenityMapper.mapToAmenityDto(amenity, new AmenityDto());
    }

    @Override
    public AmenityDto updateAmenity(Long id, AmenityDto dto) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Amenity not found"));
        amenity.setName(dto.getName());
        amenity.setDescription(dto.getDescription());
        Amenity updatedAmenity = amenityRepository.save(amenity);
        return AmenityMapper.mapToAmenityDto(updatedAmenity, new AmenityDto());
    }

    @Override
    public void deleteAmenity(Long id) {
        if (!amenityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Amenity not found");
        }
        amenityRepository.deleteById(id);
    }
}



