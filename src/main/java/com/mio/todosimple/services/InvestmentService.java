package com.mio.todosimple.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InvestmentService {
    @Value("${alpha.vantage.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public InvestmentService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getStockData(String x){
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + x + "&apikey=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }


}
