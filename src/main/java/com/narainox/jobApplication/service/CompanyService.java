package com.narainox.jobApplication.service;



import com.narainox.jobApplication.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();
    Company createCompany(Company Company);
    void deleteCompany(Long companyId);
    Company getCompany(Long companyId);
    Company updateCompany(Long companyId,Company Company);
}
