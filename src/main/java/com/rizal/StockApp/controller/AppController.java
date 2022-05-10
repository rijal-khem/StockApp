package com.rizal.StockApp.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.rizal.StockApp.StockDataService;
import com.rizal.StockApp.exceptions.StockAppExceptions;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.service.CompanyService;
import com.rizal.StockApp.validator.StockAppValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
public class AppController {

    Logger logger = LoggerFactory.getLogger(AppController.class);



    @Autowired
    StockAppValidator stockAppValidator;

    @Autowired
    CompanyService companyService;

    @Autowired
    StockDataService stockDataService;



    @PostMapping("/addCompanies")
    public ResponseEntity addCompanies(@RequestBody final List<Company> companies) throws StockAppExceptions{
        logger.info("Add companies Called");
        for(Company company:companies){
            stockAppValidator.validateAddCompanyRequest(company);
        }

        companyService.saveCompanies(companies);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved Companies Successfully");
    }


    @PostMapping("/addCompany")
    public ResponseEntity addCompany(@RequestBody final Company company) throws StockAppExceptions {
        logger.info("Add company controller called");
        stockAppValidator.validateAddCompanyRequest(company);
        companyService.saveCompany(company);

        return ResponseEntity.status(HttpStatus.CREATED).body("Saved Successfully");
    }


    @GetMapping("/startDataCollection")
    public void startStockDataCollectionManually() throws JsonProcessingException, InterruptedException {
        logger.info("Started StockData Collection Manually");
        stockDataService.updateStockData();
        logger.info("Completed: Manual Stock Data Collection");
    }

}
