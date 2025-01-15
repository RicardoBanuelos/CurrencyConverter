package com.ricardo.api.data;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRatesResponse {
    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;

    public ExchangeRatesResponse() {

    }

    public ExchangeRatesResponse(boolean success, long timestamp, String base, String date, Map<String, BigDecimal> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
