package com.finance_news_and_stock_prices.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${finnhub.api-key}")
    private String finnhubApiKey;

    @Value("${marketaux.api-key}")
    private String marketauxApiKey;

    @Bean
    public RestClient finnhubRestClient(){
        return RestClient.builder()
                .baseUrl("https://finnhub.io/api/v1")
                .defaultHeader("X-Finnhub-Token", finnhubApiKey)
                .build();
    }

    @Bean
    public RestClient marketauxRestClient(){
        return RestClient.builder()
                .baseUrl("https://api.marketaux.com/v1?api_token=" + marketauxApiKey)
                .build();
    }
}
