package com.finance_news_and_stock_prices.demo.client;

import com.finance_news_and_stock_prices.demo.dto.newsResponses.CompleteNewsResponse;
import com.finance_news_and_stock_prices.demo.exception.ExternalApiException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class NewsClient {

    private final RestClient restClient;

    private final String apiKey;

    public NewsClient(@Qualifier("marketauxRestClient") RestClient restClient,
                      @Value("${marketaux.api-key}") String apiKey){
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public CompleteNewsResponse getNewsForTicker(String symbol){
        try{
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/news/all")
                            .queryParam("symbols", symbol)
                            .queryParam("language", "en")
                            .build())
                    .retrieve()
                    .body(CompleteNewsResponse.class);
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch news for " + symbol, e);
        }
    }

    public CompleteNewsResponse getPositiveNews(String symbol){
        try{
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/news/all")
                            .queryParam("symbol", symbol)
                            .queryParam("language", "en")
                            .queryParam("sentiment_gte", 0)
                            .build())
                    .retrieve()
                    .body(CompleteNewsResponse.class);
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch positive news for " + symbol, e);
        }
    }
    public CompleteNewsResponse getNegativeNews(String symbol){
        try{
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/news/all")
                            .queryParam("symbol", symbol)
                            .queryParam("language", "en")
                            .queryParam("sentiment_lte", 0)
                            .build())
                    .retrieve()
                    .body(CompleteNewsResponse.class);
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch negative news for " + symbol, e);
        }
    }

}
