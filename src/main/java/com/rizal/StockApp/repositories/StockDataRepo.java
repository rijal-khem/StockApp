package com.rizal.StockApp.repositories;

import com.rizal.StockApp.entity.StockData;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StockDataRepo extends JpaRepository<StockData,Long> {



}
