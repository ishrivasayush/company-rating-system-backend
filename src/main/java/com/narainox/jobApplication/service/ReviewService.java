package com.narainox.jobApplication.service;

import com.narainox.jobApplication.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review createReview(Long companyId, Review review);
    Review getReview(Long companyId, Long reviewId);
    void deleteReview(Long companyId, Long reviewId);
}
