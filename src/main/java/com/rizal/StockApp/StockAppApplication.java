package com.rizal.StockApp;

import com.rizal.StockApp.Client.StockClient;
import com.rizal.StockApp.model.Company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
public class StockAppApplication {



	public static void main(String[] args) {
		SpringApplication.run(StockAppApplication.class, args);

	}

}
