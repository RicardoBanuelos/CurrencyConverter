package com.ricardo.api.configuration;

import com.ricardo.api.data.ExchangeRatesResponse;
import com.ricardo.api.data.SupportedCurrenciesResponse;
import com.ricardo.api.exceptions.AppException;
import com.ricardo.api.util.Currencies;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupportedCurrencies {
    private final ExchangeRateApiProvider exchangeRateApiProvider;
    private final RestTemplate restTemplate;

    public SupportedCurrencies(ExchangeRateApiProvider exchangeRateApiProvider, RestTemplate restTemplate) {
        this.exchangeRateApiProvider = exchangeRateApiProvider;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void getSupportedCurrencies() {
        String currenciesEndpoint = exchangeRateApiProvider.getCurrenciesEndpoint();
        String apiKey = exchangeRateApiProvider.getApiKey();

        String url = currenciesEndpoint
                + "?access_key=" + apiKey;

        ResponseEntity<SupportedCurrenciesResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, SupportedCurrenciesResponse.class);
        SupportedCurrenciesResponse exchangeRatesResponse = response.getBody();

        if(exchangeRatesResponse == null) {
            throw new AppException("Exchange Rate endpoint failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<String> supportedCurrencies = new ArrayList<>(exchangeRatesResponse.getSymbols().keySet());
        Currencies.populate(supportedCurrencies);
    }
}
