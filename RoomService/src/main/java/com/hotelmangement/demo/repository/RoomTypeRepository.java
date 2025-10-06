package com.hotelmangement.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmangement.demo.entity.RoomTypeEntity;
@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer>{
}