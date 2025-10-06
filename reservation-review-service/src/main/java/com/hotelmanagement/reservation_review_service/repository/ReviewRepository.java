package com.hotelmanagement.reservation_review_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmanagement.reservation_review_service.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRating(Integer rating);

    List<Review> findByReservationId(Long reservationId);

    List<Review> findTop5ByOrderByReviewDateDesc();

}
