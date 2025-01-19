package com.example.PetOwner.service;

import com.example.PetOwner.dtos.ReviewDTO;
import com.example.PetOwner.utils.Message;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getAllReviews();

    ReviewDTO getReviewById(Long reviewId);

    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO);

    Message deleteReview(Long reviewId);
}
