package com.rizal.StockApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rizal.StockApp.Client.StockClient;
import com.rizal.StockApp.entity.StockData;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.repositories.CompanyRepo;
import com.rizal.StockApp.repositories.StockDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockDataService {

    Logger logger = LoggerFactory.getLogger(StockDataService.class);

    @Autowired
    StockDataRepo stockDataRepo;

    @Autowired
    StockClient stockClient;

    @Autowired
    CompanyRepo companyRepo;



    @Scheduled(cron="0 0 16 * * Mon-Fri")
    public void updateStockData() throws JsonProcessingException, InterruptedException {

        logger.info("Scheduler started at {}", LocalTime.now());
        List<Company> companyList = companyRepo.findAll();

        List<StockData>  stockDataList = stockDataRepo.findAll();
        if(!companyList.isEmpty()){
            List<StockData> newStockDataList = new ArrayList<>();
            for(Company company:companyList){
                boolean isThere = stockDataList.stream().anyMatch(stockData -> stockData.getCompany().equals(company) && stockData.getDate().isEqual(LocalDate.now()));
                    if (!isThere) {
                        StockData stockData = stockClient.getStockData(company);
                        newStockDataList.add(stockData);
                    }

            }
            saveStockData(newStockDataList);
        }
        logger.info("Scheduler has completed updating data at {}",LocalTime.now());
    }


    private void saveStockData(List<StockData> stockDataList){
        stockDataRepo.saveAll(stockDataList);
        logger.info("Stock data saved.");
    }











}
