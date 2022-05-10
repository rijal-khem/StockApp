package com.rizal.StockApp.controller;


import com.rizal.StockApp.StockDataService;
import com.rizal.StockApp.entity.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockDataController {


    @Autowired
    StockDataService stockDataService;


    @GetMapping("/{company_id}/getData")
    public List<StockData> getStockDataforCompany(@PathVariable("company_id") Long company_id){
      return   stockDataService.getStockDataForCompany(company_id);
    }
}
