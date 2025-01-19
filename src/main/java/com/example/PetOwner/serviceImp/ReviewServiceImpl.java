package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.ReviewDTO;
import com.example.PetOwner.model.Review;
import com.example.PetOwner.repositories.ReviewRepository;
import com.example.PetOwner.service.ReviewService;
import com.example.PetOwner.utils.Constants;
import com.example.PetOwner.utils.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ModelMapper modelMapper;

    private ReviewDTO convertToDTO(Review review) {
        return modelMapper.map(review, ReviewDTO.class);
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        return modelMapper.map(reviewDTO, Review.class);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAllByDeletedFlagFalse();
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findByReviewIdAndDeletedFlagFalse(reviewId);
        if (review == null) {
            throw new RuntimeException("Review not found with ID: " + reviewId);
        }
        return convertToDTO(review);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedTs(LocalDateTime.now());
        review.setDeletedFlag(false);
        Review savedReview = reviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findByReviewIdAndDeletedFlagFalse(reviewId);
        if (existingReview == null) {
            throw new RuntimeException("Review not found with ID: " + reviewId);
        }
        existingReview.setRating(reviewDTO.getRating());
        existingReview.setComment(reviewDTO.getComment());
        existingReview.setUpdatedTs(LocalDateTime.now());
        Review updatedReview = reviewRepository.save(existingReview);
        return convertToDTO(updatedReview);
    }

    @Override
    public Message deleteReview(Long reviewId) {
        Review review = reviewRepository.findByReviewIdAndDeletedFlagFalse(reviewId);
        if (review == null) {
            throw new RuntimeException("Review not found with ID: " + reviewId);
        }
        review.setDeletedFlag(true);
        review.setUpdatedTs(LocalDateTime.now());
        reviewRepository.save(review);
        Message message = new Message();
        message.setMessage(Constants.SUCCESS_MESSAGE);
        message.setCode(Constants.SUCCESS_CODE);
        return message;
    }
}
