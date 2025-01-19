package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.ReviewDTO;
import com.example.PetOwner.service.ReviewService;
import com.example.PetOwner.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Get all reviews
    @GetMapping("/getAllReviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    // Get review by ID
    @GetMapping("/getReviewById")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable("reviewId") Long reviewId) {
        ReviewDTO reviewDTO = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(reviewDTO);
    }

    // Create a new review
    @PostMapping("/createReview")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    // Update a review
    @PutMapping("/updateReview")
    public ResponseEntity<ReviewDTO> updateReview(@RequestParam("reviewId") Long reviewId, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(reviewId, reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    // Soft delete a review
    @DeleteMapping("/deleteReview")
    public ResponseEntity<Message> deleteReview(@RequestParam("reviewId") Long reviewId) {
        return new ResponseEntity<>(reviewService.deleteReview(reviewId),HttpStatus.ACCEPTED);

    }
}

