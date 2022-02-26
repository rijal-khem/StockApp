package com.rizal.StockApp.validator;

import com.rizal.StockApp.exceptions.EmptyInputException;
import com.rizal.StockApp.exceptions.StockAppExceptions;
import com.rizal.StockApp.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockAppValidator {

    Logger logger = LoggerFactory.getLogger(StockAppValidator.class);


    public void validateAddCompanyRequest(Company company) throws StockAppExceptions {
        logger.info("Validating Request");

        if(company.getCompanyName()==null || company.getTickerSymbol()==null){
            logger.info("Problem occured in  Request, throwing request fields null");
            throw new StockAppExceptions("One or more input field is null. Fields can not be null. Please check.");


        }
        else if (company.getCompanyName().isEmpty() || company.getTickerSymbol().isEmpty()){
            logger.info("Problem occured in request validation, throwing empty fields.");
            throw new EmptyInputException("One or more input field is empty. Please check.");
        }
        logger.info("Request validated successfully.");

    }

}
