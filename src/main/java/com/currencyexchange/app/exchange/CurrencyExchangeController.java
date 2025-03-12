package com.currencyexchange.app.exchange;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Syed Rahat
 * @since 11 March,2025
 */
@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {

    private final CurrencyExchangeService exchangeService;
    private final DiscountService discountService;

    public CurrencyExchangeController(CurrencyExchangeService exchangeService, DiscountService discountService) {
        this.exchangeService = exchangeService;
        this.discountService = discountService;
    }

    @PostMapping("/calculate")
    public BillResponse calculateBill(@RequestBody BillRequest billRequest) {
        // Apply discount rules
        double discountedAmount = discountService.calculateDiscountedAmount(
                billRequest.getItems(),
                billRequest.getUserType(),
                billRequest.getCustomerSince()
        );

        // Convert currency
        double exchangeRate = exchangeService.getExchangeRate(billRequest.getFromCurrency(), billRequest.getToCurrency());
        double convertedAmount = discountedAmount * exchangeRate;

        // Prepare response
        BillResponse response = new BillResponse();
        response.setOriginalAmount(
                billRequest.getItems().stream().mapToDouble(item -> item.getPrice()).sum()
        );
        response.setDiscountApplied(response.getOriginalAmount() - discountedAmount);
        response.setFinalAmount(discountedAmount);
        response.setConvertedAmount(convertedAmount);
        response.setConvertedCurrency(billRequest.getToCurrency());

        return response;
    }
}
