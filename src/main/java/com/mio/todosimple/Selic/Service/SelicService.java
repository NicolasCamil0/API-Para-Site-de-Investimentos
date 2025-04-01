package com.mio.todosimple.Selic.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SelicService {
    private final RestTemplate restTemplate;

    public SelicService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getUltimaSelic() {
        String url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/dados/ultimos/1?formato=json";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            return response.getBody();
        } catch (Exception e) {
            return "Erro ao consultar SELIC: " + e.getMessage();
        }
    }

    }
