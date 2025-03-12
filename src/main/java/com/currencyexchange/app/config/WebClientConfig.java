package com.currencyexchange.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Syed Rahat
 * @since 11 March,2025
 */

@Configuration
public class WebClientConfig {

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    @Value("${exchange.api.key}")
    private String apiKey;
    
    

        @Bean
        public WebClient webClient(WebClient.Builder builder) {
            return builder.baseUrl(baseUrl)
                    .defaultHeader("apikey", apiKey) // If API key is required as a header
                    .build();
        }
    
    
    

}
