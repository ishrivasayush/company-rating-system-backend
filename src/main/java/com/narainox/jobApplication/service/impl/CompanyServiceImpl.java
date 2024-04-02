package com.narainox.jobApplication.service.impl;

import com.narainox.jobApplication.entity.Company;
import com.narainox.jobApplication.exception.RecordNotFoundException;
import com.narainox.jobApplication.repository.CompanyRepository;
import com.narainox.jobApplication.service.CompanyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;




    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        Company save = companyRepository.save(company);
        return save;
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new RecordNotFoundException("Company", "CompanyId", companyId));
        companyRepository.delete(company);
    }

    @Override
    public Company getCompany(Long companyId) {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new RecordNotFoundException("Company", "CompanyId", companyId));
        return company;
    }

    @Override
    public Company updateCompany(Long companyId, Company Company1) {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new RecordNotFoundException("Company", "CompanyId", companyId));
        company.setName(Company1.getName());
        company.setDescription(Company1.getDescription());
        Company save = companyRepository.save(company);
        return save;
    }
}
