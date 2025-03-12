package com.currencyexchange.app;

import com.currencyexchange.app.exchange.BillItem;
import com.currencyexchange.app.exchange.DiscountService;
import com.currencyexchange.app.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Syed Rahat
 * @since 12 March Jan,2025
 */

class DiscountServiceTest {

    private final DiscountService discountService = new DiscountService();

    @Test
    void testCalculateDiscountedAmount_forEmployee() {
        List<BillItem> items = List.of(
                new BillItem("Laptop", 200, "electronics"),
                new BillItem("Rice", 100, "grocery"),
                new BillItem("Shirt", 300, "clothing")
        );

        double finalAmount = discountService.calculateDiscountedAmount(items, "EMPLOYEE", LocalDate.now().minusYears(3));

        assertEquals(420.0, finalAmount, 0.01); // Expected final amount
    }


    @Test
    void testCalculateDiscountedAmount_forRegularCustomer() {
        List<BillItem> items = List.of(
                new BillItem("Shoes", 200, "clothing"),
                new BillItem("Rice", 100, "grocery"),
                new BillItem("Headphones", 200, "electronics")
        );

        String userType = "CUSTOMER";
        LocalDate customerSince = LocalDate.now().minusYears(3); // Over 2 years

        double result = discountService.calculateDiscountedAmount(items, userType, customerSince);

        assertEquals(455, result, 0.01);
    }

    @Test
    void testCalculateDiscountedAmount_noDiscountIfNewCustomer() {
        List<BillItem> items = List.of(
                new BillItem("Shoes", 200, "clothing"),
                new BillItem("Rice", 100, "grocery"),
                new BillItem("Headphones", 200, "electronics")
        );

        String userType = "CUSTOMER";
        LocalDate customerSince = LocalDate.now().minusMonths(6); // New customer (<2 years)

        double result = discountService.calculateDiscountedAmount(items, userType, customerSince);

        assertEquals(475.0, result, 0.01);
    }
    
}