package com.narainox.jobApplication.controller;


import com.narainox.jobApplication.entity.Company;
import com.narainox.jobApplication.exception.RecordNotFoundException;
import com.narainox.jobApplication.service.CompanyService;
import com.narainox.jobApplication.utils.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;


    @GetMapping
    public ResponseEntity<APIResponse> getAllCompanies()
    {
        APIResponse apiResponse=new APIResponse();
        try {
            List<Company> companies = companyService.getCompanies();
            apiResponse.setData(companies);
            apiResponse.setMessage("companies are Found");
            return new ResponseEntity<>(apiResponse, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            apiResponse.setMessage("companies are not found!");
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<APIResponse> getCompany(@PathVariable Long companyId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Company company = companyService.getCompany(companyId);
            apiResponse.setData(company);
            apiResponse.setMessage("company is Found");
            return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            apiResponse.setMessage("companies is not found.");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{companyId}")
    public ResponseEntity<APIResponse> deleteCompany(@PathVariable Long companyId)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            companyService.deleteCompany(companyId);
            apiResponse.setMessage("company is Deleted Successfully.");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            apiResponse.setMessage("company is not found.");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{companyId}")
    public ResponseEntity<APIResponse> updateCompany(@PathVariable Long companyId, @RequestBody Company company)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Company company1 = companyService.updateCompany(companyId, company);
            apiResponse.setData(company1);
            apiResponse.setMessage("company is Updated Successfully.");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (RecordNotFoundException recordNotFoundException)
        {
            apiResponse.setMessage("company is not found.");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<APIResponse> createCompany(@RequestBody Company company)
    {
        APIResponse apiResponse=new APIResponse();
        try {
            Company company1 = companyService.createCompany(company);
            apiResponse.setData(company1);
            apiResponse.setMessage("company is Created Successfully");
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
