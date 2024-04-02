package com.narainox.jobApplication.controller;


import com.narainox.jobApplication.entity.Review;
import com.narainox.jobApplication.exception.RecordNotFoundException;
import com.narainox.jobApplication.service.ReviewService;
import com.narainox.jobApplication.utils.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;
    @GetMapping("/reviews")
    public ResponseEntity<APIResponse> getAllCompanies(@PathVariable Long companyId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            List<Review> reviews = reviewService.getAllReviews(companyId);
            apiResponse.setData(reviews);
            apiResponse.setMessage("Reviews are Found");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        catch (Exception e)
        {
            apiResponse.setMessage("Reviews are not found!");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<APIResponse> getReview(@PathVariable Long reviewId,@PathVariable Long companyId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Review review = reviewService.getReview(companyId, reviewId);
            apiResponse.setData(review);
            apiResponse.setMessage("review is Found");
            return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<APIResponse> deleteCompany(@PathVariable Long companyId,@PathVariable Long reviewId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            reviewService.deleteReview(companyId,reviewId);
            apiResponse.setMessage("Review is Deleted Successfully.");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PutMapping("/{companyId}")
//    public ResponseEntity<APIResponse> updateCompany(@PathVariable Long companyId, @RequestBody Company company)
//    {
//        APIResponse apiResponse=new APIResponse();
//        try {
//            Company company1 = companyService.updateCompany(companyId, company);
//            apiResponse.setData(company1);
//            apiResponse.setMessage("company is Updated Successfully.");
//            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
//        }
//        catch (RecordNotFoundException recordNotFoundException)
//        {
//            apiResponse.setMessage("company is not found.");
//            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PostMapping("/reviews")
    public ResponseEntity<APIResponse> createReview(@RequestBody Review review,@PathVariable Long companyId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Review rev = reviewService.createReview(companyId, review);
            apiResponse.setData(rev);
            apiResponse.setMessage("Review is Created Successfully");
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
