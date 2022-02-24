package com.rizal.StockApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rizal.StockApp.Client.StockClient;
import com.rizal.StockApp.entity.StockData;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.repositories.StockDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockDataService {

    @Autowired
    StockDataRepo stockDataRepo;

    @Autowired
    StockClient stockClient;

    public void updateStockData(List<Company> companyList) throws JsonProcessingException {
        boolean updatedDataPresent= false;
        List<StockData>  stockDataList = stockDataRepo.findAll();

       for(Company company:companyList){

        boolean isThere = stockDataList.stream().anyMatch(stockData -> stockData.getCompany().equals(company) && stockData.getDate().isEqual(LocalDate.now()));
        if(!isThere){
            stockClient.getGlobalQuotesFromAlphaVantage(company);
        }
       }
    }




}
