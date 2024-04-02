package com.narainox.jobApplication.service;


import com.narainox.jobApplication.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> getJobs();
    Job getJob(Long jobId);
    void deleteJob(Long jobId);
    Job createJob(Job Job);
    Job updateJob(Job Job,Long jobId);
}
