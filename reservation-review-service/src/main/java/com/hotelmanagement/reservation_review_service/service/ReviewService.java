package com.hotelmanagement.reservation_review_service.service;

import java.util.List;

import com.hotelmanagement.reservation_review_service.dto.ReviewDto;

/**
 * Service interface for managing reviews.
 */
public interface ReviewService {

    /**
     * Creates a new review.
     *
     * @param reviewDto - DTO containing review details
     * @return the created ReviewDto
     */
    ReviewDto createReview(ReviewDto reviewDto);

    /**
     * Updates an existing review.
     *
     * @param reviewId - ID of the review to update
     * @param reviewDto - DTO containing updated review details
     * @return the updated ReviewDto
     */
    ReviewDto updateReview(Long reviewId, ReviewDto reviewDto);

    /**
     * Retrieves a review by its ID.
     *
     * @param reviewId - ID of the review
     * @return the ReviewDto corresponding to the given ID
     */
    ReviewDto getReviewById(Long reviewId);

    /**
     * Retrieves all reviews.
     *
     * @return a list of all ReviewDto objects
     */
    List<ReviewDto> getAllReviews();

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId - ID of the review to delete
     */
    void deleteReview(Long reviewId);

    /**
     * Retrieves reviews by rating.
     *
     * @param rating - rating value to filter reviews
     * @return a list of ReviewDto objects with the given rating
     */
    List<ReviewDto> getReviewsByRating(int rating);

    /**
     * Retrieves the most recent reviews.
     *
     * @return a list of the top 5 most recent ReviewDto objects
     */
    List<ReviewDto> getRecentReviews();
}
