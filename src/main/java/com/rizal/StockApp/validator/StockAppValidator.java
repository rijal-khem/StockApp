package com.rizal.StockApp.validator;

import com.rizal.StockApp.exceptions.EmptyInputException;
import com.rizal.StockApp.exceptions.StockAppExceptions;
import com.rizal.StockApp.model.Company;

public class StockAppValidator {


    public void validateAddCompanyRequest(Company company) throws StockAppExceptions {
        if(company.getCompanyName()==null || company.getTickerSymbol()==null){
            throw new StockAppExceptions("One or more input field is null. Fields can not be null. Please check.");
        }
        else if (company.getCompanyName().isEmpty() || company.getTickerSymbol().isEmpty()){
            throw new EmptyInputException("One or more input field is empty. Please check.");
        }

    }

}
