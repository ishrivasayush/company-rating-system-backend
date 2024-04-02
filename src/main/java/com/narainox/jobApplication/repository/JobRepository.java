package com.narainox.jobApplication.repository;

import com.narainox.jobApplication.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
