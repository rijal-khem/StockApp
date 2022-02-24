package com.rizal.StockApp.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rizal.StockApp.constants.AlphaVantageFunctions;
import com.rizal.StockApp.constants.ServiceConstants;
import com.rizal.StockApp.entity.StockData;
import com.rizal.StockApp.model.AlphaVantageResponse;
import com.rizal.StockApp.model.Company;
import com.rizal.StockApp.repositories.StockDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@Service
public class StockClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockDataRepo stockDataRepo;

    @Value("${api.key}")
    private String apiKey;


    String basePath = ServiceConstants.ALPHA_VANTAGE_GLOBAL_QUOTE_END_POINT;


    public AlphaVantageResponse getGlobalQuotesFromAlphaVantage(Company company) throws JsonProcessingException {

        String jsonString = restTemplate.getForObject(getUrl(company),String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        AlphaVantageResponse response = objectMapper.readValue(jsonString,AlphaVantageResponse.class);
        StockData stockData = new StockData();
        stockData.setHigh(response.getGlobalQuote().getHigh());
        stockData.setLow(response.getGlobalQuote().getLow());
        stockData.setOpen(response.getGlobalQuote().getOpen());
        stockData.setPrice(response.getGlobalQuote().getPrice());
        stockData.setVolume(response.getGlobalQuote().getVolume());
        stockData.setDate(LocalDate.now());
        stockData.setCompany(company);
        stockDataRepo.save(stockData);

        return response;
    }




    private String getUrl(Company company) {

        //Example url https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&apikey=TJ724TBI4X9K8XKF /
        String url = basePath + "function=" + AlphaVantageFunctions.GLOBAL_QUOTE + "&" + "symbol=" + company.getTickerSymbol()
                + "&" + "apikey=" + apiKey;
        return url;
    }
}
