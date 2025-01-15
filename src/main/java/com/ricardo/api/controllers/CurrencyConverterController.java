package com.ricardo.api.controllers;

import com.ricardo.api.data.CurrencyConverterResponse;
import com.ricardo.api.service.CurrencyConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class CurrencyConverterController {
    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping("/")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/convert")
    public ResponseEntity<CurrencyConverterResponse> convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount
    ) {
        BigDecimal convertedAmount = currencyConverterService.convertCurrency(from, to, amount);
        return ResponseEntity.ok(new CurrencyConverterResponse(from, to, amount, convertedAmount));
    }
}
