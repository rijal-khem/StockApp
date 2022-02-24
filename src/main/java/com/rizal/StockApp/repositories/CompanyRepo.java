package com.rizal.StockApp.repositories;

import com.rizal.StockApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {



}
