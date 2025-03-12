package com.currencyexchange.app.exchange;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Syed Rahat
 * @since 11 March Jan,2025
 */
@Service
public class DiscountService {
    
        public double calculateDiscountedAmount(List<BillItem> items, String userType, LocalDate customerSince) {
            if (items == null || items.isEmpty()) {
                return 0.0; // No items, no discount needed
            }

            double totalAmount = items.stream().mapToDouble(BillItem::getPrice).sum();
            double groceryTotal = items.stream()
                    .filter(item -> "grocery".equalsIgnoreCase(item.getCategory()))
                    .mapToDouble(BillItem::getPrice)
                    .sum();

            double nonGroceryTotal = totalAmount - groceryTotal;

            // Determine the percentage discount
            double percentageDiscount = 0.0;
            if ("EMPLOYEE".equalsIgnoreCase(userType)) {
                percentageDiscount = 0.30; // 30% for employees
            } else if ("AFFILIATE".equalsIgnoreCase(userType)) {
                percentageDiscount = 0.10; // 10% for affiliates
            } else if (customerSince != null && Period.between(customerSince, LocalDate.now()).getYears() > 2) {
                percentageDiscount = 0.05; // 5% for long-term customers
            }

            // Apply the percentage discount only on non-grocery items
            double discountFromPercentage = nonGroceryTotal * percentageDiscount;

            // Apply the $5 discount for every $100 spent (on the total bill)
            double discountFromEvery100Dollars = Math.floor(totalAmount / 100) * 5;

            // Calculate the final payable amount
            return totalAmount - discountFromPercentage - discountFromEvery100Dollars;
        
    }
}
