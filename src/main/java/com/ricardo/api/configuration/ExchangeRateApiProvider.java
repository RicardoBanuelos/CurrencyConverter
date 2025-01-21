package com.ricardo.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateApiProvider {
    @Value("${api.currencies_endpoint}")
    private String currenciesEndpoint;

    @Value("${api.rates_endpoint}")
    private String ratesEndpoint;

    @Value("${api.key}")
    private String apiKey;

    public String getCurrenciesEndpoint() {
        return currenciesEndpoint;
    }

    public String getRatesEndpoint() {
        return ratesEndpoint;
    }

    public String getApiKey() {
        return apiKey;
    }
}
