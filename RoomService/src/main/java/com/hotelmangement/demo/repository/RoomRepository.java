package com.hotelmangement.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmangement.demo.entity.RoomEntity;
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer>{

	Optional<RoomEntity>getByRoomId(int roomId);
	List<RoomEntity> findByRoomType_RoomTypeId(int roomTypeId);
}