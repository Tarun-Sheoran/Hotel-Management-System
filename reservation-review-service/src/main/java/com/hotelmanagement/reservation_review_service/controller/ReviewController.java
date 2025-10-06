package com.hotelmanagement.reservation_review_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotelmanagement.reservation_review_service.dto.ReviewDto;
import com.hotelmanagement.reservation_review_service.dto.ResponseDto;
import com.hotelmanagement.reservation_review_service.service.ReviewService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/review")
@Tag(
    name = "Review Management REST APIs",
    description = "CRUD REST APIs to create, update, fetch, delete and filter reviews"
)
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(
        summary = "Create Review",
        description = "Creates a new review for a reservation"
    )
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Review created successfully"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> createReview(@RequestBody ReviewDto reviewDto) {
        reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("POSTSUCCESS", "Review added successfully"));
    }

    @Operation(
        summary = "Update Review",
        description = "Updates an existing review by review ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review updated successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<ResponseDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewDto reviewDto) {
        reviewService.updateReview(reviewId, reviewDto);
        return ResponseEntity.ok(new ResponseDto("UPDATESUCCESS", "Review updated successfully"));
    }

    @Operation(
        summary = "Get Review by ID",
        description = "Fetches review details using review ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long reviewId) {
        ReviewDto review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @Operation(
        summary = "Get All Reviews",
        description = "Fetches all reviews from the system"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reviews fetched successfully"),
        @ApiResponse(responseCode = "404", description = "No reviews found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @Operation(
        summary = "Delete Review",
        description = "Deletes a review by review ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<ResponseDto> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(new ResponseDto("DELETESUCCESS", "Review deleted successfully"));
    }

    @Operation(
        summary = "Get Reviews by Rating",
        description = "Fetches reviews filtered by rating value"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reviews fetched successfully"),
        @ApiResponse(responseCode = "404", description = "No reviews found with the given rating")
    })
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<ReviewDto>> getReviewsByRating(@PathVariable int rating) {
        List<ReviewDto> reviews = reviewService.getReviewsByRating(rating);
        return ResponseEntity.ok(reviews);
    }

    @Operation(
        summary = "Get Recent Reviews",
        description = "Fetches the top 5 most recent reviews"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recent reviews fetched successfully"),
        @ApiResponse(responseCode = "404", description = "No recent reviews found")
    })
    @GetMapping("/recent")
    public ResponseEntity<List<ReviewDto>> getRecentReviews() {
        List<ReviewDto> reviews = reviewService.getRecentReviews();
        return ResponseEntity.ok(reviews);
    }
}