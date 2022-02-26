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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StockClient {

    Logger logger = LoggerFactory.getLogger(StockClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockDataRepo stockDataRepo;

    @Value("${api.key}")
    private String apiKey;

    private int apiCallCount=0;


    String basePath = ServiceConstants.ALPHA_VANTAGE_GLOBAL_QUOTE_END_POINT;





    public StockData getStockData(Company company) throws JsonProcessingException, InterruptedException {

        String jsonString = getDataFromExternalApi(company);
        AlphaVantageResponse alphaVantageResponse = mapAlphaVantageResponeToObject(jsonString);
        StockData stockData = setStockObject(alphaVantageResponse, company);
        return stockData;
    }

    private StockData setStockObject(AlphaVantageResponse alphaVantageResponse, Company company) {

        StockData stockData = new StockData();
        stockData.setCompany(company);
        stockData.setDate(alphaVantageResponse.getGlobalQuote().getLatestTradingDay());
        stockData.setVolume(alphaVantageResponse.getGlobalQuote().getVolume());
        stockData.setPrice(alphaVantageResponse.getGlobalQuote().getPrice());
        stockData.setOpen(alphaVantageResponse.getGlobalQuote().getOpen());
        stockData.setHigh(alphaVantageResponse.getGlobalQuote().getHigh());
        stockData.setLow(alphaVantageResponse.getGlobalQuote().getLow());
        return stockData;
    }


    private String getDataFromExternalApi(Company company) throws InterruptedException {
        logger.info("Calling AlphaVantage end point.");

        if (apiCallCount != 0 && apiCallCount >= 5) {
            logger.info("Thread Sleeping. 5 calls are already made to AlphaVantage.");
            Thread.sleep(60000);
            apiCallCount = 0;
            logger.info("Thread slept 1 min. Now the api call count is :{}", apiCallCount);
        }
        String jsonString = restTemplate.getForObject(getUrl(company), String.class);
        logger.info("Received the response from AlphaVantage. The api call count is : {}",apiCallCount);
        apiCallCount++;

        return jsonString;
    }


    private String getUrl(Company company) {

        //Example url https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&apikey=TJ724TBI4X9K8XKF /
        String url = basePath + "function=" + AlphaVantageFunctions.GLOBAL_QUOTE + "&" + "symbol=" + company.getTickerSymbol()
                + "&" + "apikey=" + apiKey;
        return url;
    }

    private  AlphaVantageResponse mapAlphaVantageResponeToObject(String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try{
            AlphaVantageResponse response = objectMapper.readValue(jsonString,AlphaVantageResponse.class);
            return response;

        }catch (RuntimeException | JsonProcessingException e){
            logger.debug("Exception occurred at mapping json to object. Exception is {}", e.getMessage());
        }
        return null;

    }
}
