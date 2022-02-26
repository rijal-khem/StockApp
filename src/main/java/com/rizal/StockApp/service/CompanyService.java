package com.rizal.StockApp.service;

import com.rizal.StockApp.exceptions.StockAppExceptions;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.repositories.CompanyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    CompanyRepo companyRepo;


    public void saveCompany(Company company) {

        if(ifAlreadyExists(company)){
          logger.info("Company Already Exists");
          throw new StockAppExceptions("The Company :{} already exists.");
        }
        else{
            companyRepo.save(company);
        }
    }

    public void saveCompanies(List<Company> companies) {

       List<Company> listOfCompaniesToSave= companies.stream()
               .filter(company -> !ifAlreadyExists(company))
               .collect(Collectors.toList());
       if(!listOfCompaniesToSave.isEmpty()){
           companyRepo.saveAll(listOfCompaniesToSave);
           logger.info("Companies Saved are {}",listOfCompaniesToSave);
       }else throw new StockAppExceptions("Companies already present");
    }

    private boolean ifAlreadyExists(Company company){
        List<Company> presentCompanies = companyRepo.findAll();
        if(presentCompanies.contains(company)){
            return true;
        }
        else{
            return false;
        }

    }
}
