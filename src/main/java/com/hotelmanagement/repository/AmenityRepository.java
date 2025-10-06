package com.hotelmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagement.entity.Amenity;

import java.util.Optional;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(String name);
}
