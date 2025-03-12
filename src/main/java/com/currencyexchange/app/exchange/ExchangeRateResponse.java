package com.currencyexchange.app.exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author Syed Rahat
 * @since 11 March Jan,2025
 */

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateResponse {
    private String base;
    private Map<String, Double> rates;
}