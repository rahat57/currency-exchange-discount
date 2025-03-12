package com.currencyexchange.app.exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Syed Rahat
 * @since 11 march,2025
 */

@Getter
@Setter
@NoArgsConstructor
public class BillRequest {
    private List<BillItem> items;
    private String fromCurrency;
    private String toCurrency;
    private String userType; // EMPLOYEE, AFFILIATE, CUSTOMER
    private LocalDate customerSince;
}
