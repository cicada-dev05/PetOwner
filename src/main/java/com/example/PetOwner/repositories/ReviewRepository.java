package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findAllByDeletedFlagFalse();

    Review findByReviewIdAndDeletedFlagFalse(Long reviewId);
}
