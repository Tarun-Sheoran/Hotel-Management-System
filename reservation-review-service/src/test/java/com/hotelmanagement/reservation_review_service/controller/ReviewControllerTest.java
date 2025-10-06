package com.hotelmanagement.reservation_review_service.controller;

import com.hotelmanagement.reservation_review_service.dto.ReviewDto;
import com.hotelmanagement.reservation_review_service.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReviewControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ReviewService reviewService;

	@InjectMocks
	private ReviewController reviewController;

	private ReviewDto sampleReview;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

		sampleReview = new ReviewDto();
		sampleReview.setReservationId(101L);
		sampleReview.setRating(5);
		sampleReview.setComment("Excellent stay!");
		sampleReview.setReviewDate(LocalDate.of(2025, 2, 20));
	}

	@Test
	void createReview_returnsCreated() throws Exception {
		String json = """
				{
				    "reservationId": 101,
				    "rating": 5,
				    "comment": "Excellent stay!",
				    "reviewDate": "2025-02-20"
				}
				""";

		mockMvc.perform(post("/api/review/post").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.statusCode").value("POSTSUCCESS"));
	}

	@Test
	void updateReview_returnsOk() throws Exception {
		String json = """
				{
				    "reservationId": 101,
				    "rating": 4,
				    "comment": "Very good experience!",
				    "reviewDate": "2025-02-21"
				}
				""";

		mockMvc.perform(put("/api/review/update/1").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.statusCode").value("UPDATESUCCESS"));
	}

	@Test
	void getReviewById_returnsOk() throws Exception {
		when(reviewService.getReviewById(1L)).thenReturn(sampleReview);

		mockMvc.perform(get("/api/review/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.reservationId").value(101)).andExpect(jsonPath("$.rating").value(5));
	}

	@Test
	void getAllReviews_returnsOk() throws Exception {
		when(reviewService.getAllReviews()).thenReturn(Collections.singletonList(sampleReview));

		mockMvc.perform(get("/api/review/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].comment").value("Excellent stay!"));
	}

	@Test
	void deleteReview_returnsOk() throws Exception {
		doNothing().when(reviewService).deleteReview(1L);

		mockMvc.perform(delete("/api/review/delete/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value("DELETESUCCESS"));
	}

	@Test
	void getReviewsByRating_returnsOk() throws Exception {
		when(reviewService.getReviewsByRating(5)).thenReturn(Collections.singletonList(sampleReview));

		mockMvc.perform(get("/api/review/rating/5")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].rating").value(5));
	}

	@Test
	void getRecentReviews_returnsOk() throws Exception {
		when(reviewService.getRecentReviews()).thenReturn(Collections.singletonList(sampleReview));

		mockMvc.perform(get("/api/review/recent")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].reviewDate[0]").value(2025))
				.andExpect(jsonPath("$[0].reviewDate[1]").value(2)).andExpect(jsonPath("$[0].reviewDate[2]").value(20));

	}
}