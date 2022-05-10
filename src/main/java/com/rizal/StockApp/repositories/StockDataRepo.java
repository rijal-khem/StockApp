package com.rizal.StockApp.repositories;

import com.rizal.StockApp.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StockDataRepo extends JpaRepository<StockData,Long> {


    @Query(value = "select * from stock_data where stock_data.company_company_id= :id ORDER BY stock_data.date ASC",nativeQuery = true)
    List<StockData> getStockDataForCompany(@Param("id") Long company_id);

}
