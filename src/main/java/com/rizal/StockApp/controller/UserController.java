package com.rizal.StockApp.controller;

import com.rizal.StockApp.entity.StockData;
import com.rizal.StockApp.entity.User;
import com.rizal.StockApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity userRegistration(@RequestBody final User user){
        User savedUser = userService.registerUser(user);
        Integer id = savedUser.getUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }


    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Integer id){
       return userService.getUserById(id).toString();
    }

    @PostMapping("/{user_id}/add_company_watch_list/{company_id}")
    public ResponseEntity addCompanyToWatchList(@PathVariable("user_id") Integer user_id, @PathVariable("company_id") Long company_id){
        userService.updateUserCompanies(user_id, company_id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{user_id}/viewMyStock")
    public List<List<StockData>> viewUserStock(@PathVariable("user_id")Integer id){
        return userService.viewUserStockData(id);
    }



}
