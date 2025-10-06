package com.hotelmanagement.reservation_review_service.service;

import com.hotelmanagement.reservation_review_service.dto.ReviewDto;
import com.hotelmanagement.reservation_review_service.entity.Review;
import com.hotelmanagement.reservation_review_service.exception.ResourceNotFoundException;
import com.hotelmanagement.reservation_review_service.exception.ReviewAlreadyExistsException;
import com.hotelmanagement.reservation_review_service.repository.ReviewRepository;
import com.hotelmanagement.reservation_review_service.service.impl.ReviewServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

	@Mock
	private ReviewRepository reviewRepository;

	@InjectMocks
	private ReviewServiceImpl reviewService;

	private ReviewDto reviewDto;
	private Review review;

	@BeforeEach
	void setUp() {
		reviewDto = new ReviewDto();
		reviewDto.setReservationId(101L);
		reviewDto.setRating(5);
		reviewDto.setComment("Excellent stay!");
		reviewDto.setReviewDate(LocalDate.of(2025, 2, 20));

		review = new Review();
		review.setReviewId(1L);
		review.setReservationId(101L);
		review.setRating(5);
		review.setComment("Excellent stay!");
		review.setReviewDate(LocalDate.of(2025, 2, 20));
	}

	@Test
	void createReview_success() {
		when(reviewRepository.findByReservationId(101L)).thenReturn(Collections.emptyList());
		when(reviewRepository.save(any(Review.class))).thenReturn(review);

		ReviewDto result = reviewService.createReview(reviewDto);

		assertNotNull(result);
		assertEquals(101L, result.getReservationId());
		verify(reviewRepository).save(any(Review.class));
	}

	@Test
	void createReview_alreadyExists_throwsException() {
		when(reviewRepository.findByReservationId(101L)).thenReturn(List.of(review));

		assertThrows(ReviewAlreadyExistsException.class, () -> reviewService.createReview(reviewDto));
		verify(reviewRepository, never()).save(any());
	}

	@Test
	void updateReview_success() {
		when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
		when(reviewRepository.save(any(Review.class))).thenReturn(review);

		ReviewDto updatedDto = new ReviewDto();
		updatedDto.setReservationId(101L);
		updatedDto.setRating(4);
		updatedDto.setComment("Very good");
		updatedDto.setReviewDate(LocalDate.of(2025, 2, 21));

		ReviewDto result = reviewService.updateReview(1L, updatedDto);

		assertEquals(4, result.getRating());
		assertEquals("Very good", result.getComment());
		verify(reviewRepository).save(any(Review.class));
	}

	@Test
	void updateReview_notFound_throwsException() {
		when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> reviewService.updateReview(1L, reviewDto));
	}

	@Test
	void getReviewById_success() {
		when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

		ReviewDto result = reviewService.getReviewById(1L);

		assertEquals(101L, result.getReservationId());
	}

	@Test
	void getReviewById_notFound_throwsException() {
		when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> reviewService.getReviewById(1L));
	}

	@Test
	void getAllReviews_success() {
		when(reviewRepository.findAll()).thenReturn(List.of(review));

		List<ReviewDto> result = reviewService.getAllReviews();

		assertEquals(1, result.size());
	}

	@Test
	void getAllReviews_empty_throwsException() {
		when(reviewRepository.findAll()).thenReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> reviewService.getAllReviews());
	}

	@Test
	void deleteReview_success() {
		when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
		doNothing().when(reviewRepository).delete(review);

		assertDoesNotThrow(() -> reviewService.deleteReview(1L));
		verify(reviewRepository).delete(review);
	}

	@Test
	void deleteReview_notFound_throwsException() {
		when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview(1L));
	}

	@Test
	void getReviewsByRating_success() {
		when(reviewRepository.findByRating(5)).thenReturn(List.of(review));

		List<ReviewDto> result = reviewService.getReviewsByRating(5);

		assertEquals(1, result.size());
	}

	@Test
	void getReviewsByRating_empty_throwsException() {
		when(reviewRepository.findByRating(5)).thenReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> reviewService.getReviewsByRating(5));
	}

	@Test
	void getRecentReviews_success() {
		when(reviewRepository.findTop5ByOrderByReviewDateDesc()).thenReturn(List.of(review));

		List<ReviewDto> result = reviewService.getRecentReviews();

		assertEquals(1, result.size());
	}

	@Test
	void getRecentReviews_empty_throwsException() {
		when(reviewRepository.findTop5ByOrderByReviewDateDesc()).thenReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> reviewService.getRecentReviews());
	}
}