package com.ricardo.api.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Currencies {
    private static final Set<String> currencies = new HashSet<>();

    public static boolean isCurrencyValid(String currency) {
        return currencies.contains(currency);
    }

    public static void populate(List<String> supportedCurrencies) {
        currencies.clear();
        currencies.addAll(supportedCurrencies);
    }
}
