package com.currencyexchange.app.exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Syed Rahat
 * @since 11 March Jan,2025
 */
@Getter
@Setter
@NoArgsConstructor
public class BillResponse {
    private double originalAmount;
    private double discountApplied;
    private double finalAmount;
    private double convertedAmount;
    private String convertedCurrency;
}