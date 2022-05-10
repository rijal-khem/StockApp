package com.rizal.StockApp.service;

import com.rizal.StockApp.entity.StockData;
import com.rizal.StockApp.entity.User;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.repositories.StockDataRepo;
import com.rizal.StockApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StockDataRepo stockDataRepo;


    public User registerUser(final User user){
        return userRepo.save(user);
    }

    public void  updateUserCompanies(Integer user_id, Long company_id){
        User user = userRepo.getById(user_id);
        user.getCompanies().add(company_id);
        userRepo.save(user);
    }

    public User getUserById(Integer userId){
        return userRepo.getById(userId);
    }


    public List<List<StockData>> viewUserStockData(Integer user_id) {
        List<List<StockData>> userStockData = new ArrayList<>();
        User user = userRepo.getById(user_id);
        List<Long> userCompanies = user.getCompanies();
        userCompanies.forEach(id->userStockData.add(stockDataRepo.getStockDataForCompany(id)));
        return userStockData;
    }
}
