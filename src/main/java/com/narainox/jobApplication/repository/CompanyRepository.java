package com.narainox.jobApplication.repository;

import com.narainox.jobApplication.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
