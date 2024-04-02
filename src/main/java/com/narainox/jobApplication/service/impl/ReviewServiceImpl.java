package com.narainox.jobApplication.service.impl;

import com.narainox.jobApplication.entity.Company;
import com.narainox.jobApplication.entity.Review;
import com.narainox.jobApplication.exception.RecordNotFoundException;
import com.narainox.jobApplication.repository.ReviewRepository;
import com.narainox.jobApplication.service.CompanyService;
import com.narainox.jobApplication.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        Company company = companyService.getCompany(companyId);
        review.setCompany(company);
        Review save = reviewRepository.save(review);
        return save;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(()->new RecordNotFoundException("Review","ReviewId",reviewId));
    }

    @Override
    public void deleteReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Review rev = reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("Review", "ReviewId", reviewId));
        reviewRepository.delete(rev);
    }

}
