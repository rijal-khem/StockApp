package com.rizal.StockApp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.rizal.StockApp.Client.StockClient;
import com.rizal.StockApp.StockDataService;
import com.rizal.StockApp.entity.StockData;
import com.rizal.StockApp.exceptions.StockAppExceptions;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.repositories.CompanyRepo;
import com.rizal.StockApp.repositories.StockDataRepo;
import com.rizal.StockApp.validator.StockAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AppController {

    @Autowired
    StockClient stockClient;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    StockAppValidator stockAppValidator;

    @Autowired
    StockDataRepo stockDataRepo;

    @Autowired
    StockDataService stockDataService;



    @PutMapping("/addCompany")
    public void addCompany(@RequestBody final Company company) throws StockAppExceptions {
        stockAppValidator.validateAddCompanyRequest(company);

        boolean isCompanyAlreadyPresent = false;
        List<Company> presentCompanies = companyRepo.findAll();
        for(Company comp:presentCompanies){
            if(comp.equals(company)){
                isCompanyAlreadyPresent = true;
                throw new StockAppExceptions("Company already exists.");
            }
        }
        if(!isCompanyAlreadyPresent){
            companyRepo.save(company);
        }

    }


    @GetMapping("/url")
    public void  getUrl() throws JsonProcessingException {
        List<Company> companyList = companyRepo.findAll();

        stockDataService.updateStockData(companyList);

    }
}
