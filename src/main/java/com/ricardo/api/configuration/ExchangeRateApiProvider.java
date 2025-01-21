package com.ricardo.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateApiProvider {
    @Value("${CURRENCIES_ENDPOINT}")
    private String currenciesEndpoint;

    @Value("${RATES_ENDPOINT}")
    private String ratesEndpoint;

    @Value("${API_KEY}")
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
