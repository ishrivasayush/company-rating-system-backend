package com.narainox.jobApplication.service.impl;


import com.narainox.jobApplication.entity.Job;
import com.narainox.jobApplication.exception.RecordNotFoundException;
import com.narainox.jobApplication.repository.JobRepository;
import com.narainox.jobApplication.service.JobService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;



    @Override
    public Job getJob(Long jobId) {
        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(() -> new RecordNotFoundException("Job", "JobId", jobId));
        return job;
    }

    @Override
    public void deleteJob(Long jobId) {
        Job job = jobRepository
                .findById(jobId)
                .orElseThrow(() -> new RecordNotFoundException("Job", "JobId", jobId));
        jobRepository.delete(job);
    }


    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);

    }

    @Override
    public Job updateJob(Job job, Long jobId) {
        Job job1 = jobRepository
                .findById(jobId)
                .orElseThrow(() -> new RecordNotFoundException("Job", "JobId", jobId));
        job1.setLocation(job.getLocation());
        job1.setDescription(job.getDescription());
        job1.setTitle(job.getTitle());
        job1.setMinSalary(job.getMinSalary());
        job1.setMaxSalary(job.getMaxSalary());
        return jobRepository.save(job);
    }

}
