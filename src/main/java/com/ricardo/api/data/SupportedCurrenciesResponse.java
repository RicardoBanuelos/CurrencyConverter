package com.ricardo.api.data;

import java.util.Map;

public class SupportedCurrenciesResponse {
    private boolean success;
    Map<String, String> symbols;

    public SupportedCurrenciesResponse() {
    }

    public SupportedCurrenciesResponse(boolean success, Map<String, String> symbols) {
        this.success = success;
        this.symbols = symbols;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, String> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, String> symbols) {
        this.symbols = symbols;
    }
}
