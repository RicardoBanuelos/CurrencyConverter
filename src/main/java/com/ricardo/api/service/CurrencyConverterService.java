package com.ricardo.api.service;

import com.ricardo.api.configuration.ExchangeRateApiProvider;
import com.ricardo.api.data.ExchangeRatesResponse;
import com.ricardo.api.exceptions.AppException;
import com.ricardo.api.util.Currencies;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyConverterService {
    private final ExchangeRateApiProvider exchangeRateApiProvider;
    private final RestTemplate restTemplate;

    public CurrencyConverterService(ExchangeRateApiProvider exchangeRateApiProvider, RestTemplate restTemplate) {
        this.exchangeRateApiProvider = exchangeRateApiProvider;
        this.restTemplate = restTemplate;
    }

    public BigDecimal convertCurrency(String from, String to, BigDecimal amount) {
        if(!Currencies.isCurrencyValid(from)) {
            throw new AppException("Invalid From Currency", HttpStatus.BAD_REQUEST);
        }

        if(!Currencies.isCurrencyValid(to)) {
            throw new AppException("Invalid To Currency", HttpStatus.BAD_REQUEST);
        }

        if(amount.intValue() <= 0) {
            throw new AppException("Invalid Amount", HttpStatus.BAD_REQUEST);
        }

        String endpoint = exchangeRateApiProvider.getRatesEndpoint();
        String key = exchangeRateApiProvider.getApiKey();

        String url = endpoint
                  + "?access_key=" + key
                  + "&symbols=" + from + "," + to;

        ResponseEntity<ExchangeRatesResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, ExchangeRatesResponse.class);
        ExchangeRatesResponse exchangeRatesResponse = response.getBody();

        if(exchangeRatesResponse == null) {
            throw new AppException("Error calling exchange rates endpoint", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BigDecimal fromValue = exchangeRatesResponse.getRates().get(from);
        BigDecimal toValue = exchangeRatesResponse.getRates().get(to);
        BigDecimal conversion = BigDecimal.valueOf(toValue.doubleValue() / fromValue.doubleValue());

        return BigDecimal.valueOf(conversion.doubleValue() * amount.doubleValue());
    }
}
