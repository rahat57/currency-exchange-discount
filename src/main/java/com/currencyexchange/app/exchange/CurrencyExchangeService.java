package com.currencyexchange.app.exchange;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * @author Syed Rahat
 * @since 11 March Jan,2025
 */
@Service
public class CurrencyExchangeService {
    private final WebClient webClient;

    public CurrencyExchangeService(WebClient webClient) {
        this.webClient = webClient;
    }

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        String apiUrl = "/" + fromCurrency; // Fetch latest exchange rates for base currency

        Map<String, Object> response = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // Blocking call for simplicity, can be made reactive

        if (response != null && response.containsKey("rates")) {
            Map<String, Double> rates = (Map<String, Double>) response.get("rates");
            return rates.getOrDefault(toCurrency, 1.0);
        }

        return 1.0; // Default fallback rate (same currency)
    }
}
