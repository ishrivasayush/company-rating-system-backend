package com.narainox.jobApplication.controller;


import com.narainox.jobApplication.entity.Job;
import com.narainox.jobApplication.exception.RecordNotFoundException;
import com.narainox.jobApplication.service.JobService;
import com.narainox.jobApplication.utils.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@AllArgsConstructor
public class JobController {
    private JobService jobService;

    @GetMapping
    public ResponseEntity<APIResponse> getAllJobs()
    {
        APIResponse apiResponse=new APIResponse();
        try {
            List<Job> Jobs = jobService.getJobs();
            apiResponse.setData(Jobs);
            apiResponse.setMessage("Jobs are Found");
            return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            apiResponse.setMessage("Jobs are not found!");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<APIResponse> getJob(@PathVariable Long jobId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Job job= jobService.getJob(jobId);
            apiResponse.setData(job);
            apiResponse.setMessage("Job is Found");
            return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            apiResponse.setMessage("Job is not found.");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{jobId}")
    public ResponseEntity<APIResponse> deleteJob(@PathVariable Long jobId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            jobService.deleteJob(jobId);
            apiResponse.setMessage("Job is Deleted Successfully.");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            apiResponse.setMessage("Job is not found.");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<APIResponse> updateJob(@PathVariable Long jobId,@RequestBody Job job)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Job job1 = jobService.updateJob(job, jobId);
            apiResponse.setData(job1);
            apiResponse.setMessage("Job is Updated Successfully.");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            apiResponse.setMessage("Job is not found.");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<APIResponse> createJob(@RequestBody Job job)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Job job1 = jobService.createJob(job);
            apiResponse.setData(job1);
            apiResponse.setMessage("Job is Created Successfully");
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
