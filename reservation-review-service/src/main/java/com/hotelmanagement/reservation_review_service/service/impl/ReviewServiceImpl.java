package com.hotelmanagement.reservation_review_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.reservation_review_service.dto.ReviewDto;
import com.hotelmanagement.reservation_review_service.entity.Review;
import com.hotelmanagement.reservation_review_service.exception.ResourceNotFoundException;
import com.hotelmanagement.reservation_review_service.exception.ReviewAlreadyExistsException;
import com.hotelmanagement.reservation_review_service.repository.ReviewRepository;
import com.hotelmanagement.reservation_review_service.service.ReviewService;

/**
 * Implementation of the ReviewService interface.
 * Handles business logic related to review operations.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Creates a new review if no existing review is found for the same reservation ID.
     *
     * @param dto - ReviewDto containing review details
     * @return the created ReviewDto
     * @throws ReviewAlreadyExistsException if a review already exists for the given reservation ID
     */
    @Override
    public ReviewDto createReview(ReviewDto dto) {
        List<Review> existingReviews = reviewRepository.findByReservationId(dto.getReservationId());
        if (!existingReviews.isEmpty()) {
            throw new ReviewAlreadyExistsException(
                    "Review already exists for reservation ID: " + dto.getReservationId());
        }

        Review review = mapToEntity(dto);
        Review saved = reviewRepository.save(review);
        return mapToDto(saved);
    }

    /**
     * Updates an existing review.
     *
     * @param reviewId - ID of the review to update
     * @param dto - ReviewDto containing updated review details
     * @return the updated ReviewDto
     * @throws ResourceNotFoundException if the review is not found
     */
    @Override
    public ReviewDto updateReview(Long reviewId, ReviewDto dto) {
        Review existing = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        existing.setReservationId(dto.getReservationId());
        existing.setRating(dto.getRating());
        existing.setComment(dto.getComment());
        existing.setReviewDate(dto.getReviewDate());

        Review updated = reviewRepository.save(existing);
        return mapToDto(updated);
    }

    /**
     * Retrieves a review by its ID.
     *
     * @param reviewId - ID of the review
     * @return the corresponding ReviewDto
     * @throws ResourceNotFoundException if the review is not found
     */
    @Override
    public ReviewDto getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        return mapToDto(review);
    }

    /**
     * Retrieves all reviews.
     *
     * @return a list of ReviewDto objects
     * @throws ResourceNotFoundException if no reviews are found
     */
    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("Review list is empty");
        }
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Deletes a review by its ID.
     *
     * @param reviewId - ID of the review to delete
     * @throws ResourceNotFoundException if the review is not found
     */
    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        reviewRepository.delete(review);
    }

    /**
     * Retrieves reviews by rating.
     *
     * @param rating - rating value to filter reviews
     * @return a list of ReviewDto objects with the given rating
     * @throws ResourceNotFoundException if no reviews are found with the given rating
     */
    @Override
    public List<ReviewDto> getReviewsByRating(int rating) {
        List<Review> reviews = reviewRepository.findByRating(rating);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No reviews found with rating: " + rating);
        }
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Retrieves the most recent reviews (top 5).
     *
     * @return a list of the top 5 most recent ReviewDto objects
     * @throws ResourceNotFoundException if no recent reviews are found
     */
    @Override
    public List<ReviewDto> getRecentReviews() {
        List<Review> reviews = reviewRepository.findTop5ByOrderByReviewDateDesc();
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No recent reviews found");
        }
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Converts a Review entity to a ReviewDto.
     *
     * @param review - Review entity
     * @return ReviewDto
     */
    private ReviewDto mapToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setReservationId(review.getReservationId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setReviewDate(review.getReviewDate());
        return dto;
    }

    /**
     * Converts a ReviewDto to a Review entity.
     *
     * @param dto - ReviewDto
     * @return Review entity
     */
    private Review mapToEntity(ReviewDto dto) {
        Review review = new Review();
        review.setReservationId(dto.getReservationId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setReviewDate(dto.getReviewDate());
        return review;
    }
}
