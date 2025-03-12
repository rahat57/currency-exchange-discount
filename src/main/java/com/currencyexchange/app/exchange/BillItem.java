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
public class BillItem {
    private String name;
    private double price;
    private String category; // GROCERY or NON-GROCERY

    public BillItem(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
}