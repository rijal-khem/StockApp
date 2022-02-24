package com.rizal.StockApp;

import com.rizal.StockApp.validator.StockAppValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemaplate(){
        return new RestTemplate();
    }

    @Bean
    public StockAppValidator getSockAppValidator(){
        return new StockAppValidator();
    }




}
