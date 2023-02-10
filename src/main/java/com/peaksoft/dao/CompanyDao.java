package com.peaksoft.dao;

import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;

import java.util.List;

public interface CompanyDao {

     List<Company>getAllCompanies();
     void addCompany(Company company);
     Company getCompanyById(Long id);
     void updateCompany(Company company,Long id);
     void deleteCompany(Company company);

}
