package com.mio.todosimple.BolsaValores;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

@Service
public class BolsaService {

    public final RestTemplate restTemplate;
    public final ObjectMapper objectMapper;

    public BolsaService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Value("${rapidapi.key}")
    private String apiKey;


    public String cotacao() {
        String url = "https://yahoo-finance15.p.rapidapi.com/api/v1/markets/stock/quotes?ticker=AAPL,MSFT,^SPX,^NYA,GAZP.ME,SIBN.ME,GEECEE.NS";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", apiKey);
        headers.set("x-rapidapi-host", "yahoo-finance15.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}