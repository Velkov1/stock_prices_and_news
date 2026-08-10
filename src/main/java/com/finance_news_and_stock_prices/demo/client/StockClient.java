package com.finance_news_and_stock_prices.demo.client;

import com.finance_news_and_stock_prices.demo.dto.stockResponses.*;
import com.finance_news_and_stock_prices.demo.exception.ExternalApiException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StockClient {

    private final RestClient restClient;

    public StockClient(@Qualifier("finnhubRestClient") RestClient restClient){
        this.restClient = restClient;
    }

    //methods that fetch the data from the api

    public StockPriceResponse getPrice(TickerDTO ticker){
        try{
            FinnhubQuoteResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/quote")
                            .queryParam("symbol", ticker.getTicker())
                            .build())
                    .retrieve()
                    .body(FinnhubQuoteResponse.class);

            return new StockPriceResponse(
                    ticker,
                    response.getCurrentPrice(),
                    Instant.ofEpochSecond(response.getTimestamp())
            );
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch price for " + ticker.getTicker(), e);
        }
    }

    public StockProfileResponse getProfileData(String symbol) {
        try{
            FinnhubProfileResponse response =  restClient.get()
                    .uri(uriBuilder ->uriBuilder
                                    .path("stock/profile")
                                    .queryParam("symbol", symbol)
                                    .build()
                            )
                    .retrieve()
                    .body(FinnhubProfileResponse.class);
            return new StockProfileResponse(
                    response.getTicker(),
                    response.getName(),
                    response.getCountry(),
                    response.getCurrency(),
                    response.getMarketCapitalization(),
                    response.getLogo(),
                    response.getWeburl()
            );
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch profile data for " + symbol, e);
        }
    }

    public List<SummaryTickerDTO> getAllProfiles(){
        try{
            List<FinnhubSymbolResponse> responses = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("stock/symbol/?exchange=US")
                            .build()
                    )
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<FinnhubSymbolResponse>>() {});
            List<SummaryTickerDTO> result = new ArrayList<>();
            for(FinnhubSymbolResponse stock : responses){
                SummaryTickerDTO converted = new SummaryTickerDTO(stock.getDisplaySymbol(), stock.getDescription());
                result.add(converted);
            }
            return result;
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch all profiles", e);
        }
    }

    public List<String> getAllPeers(String symbol){
        try{
            String[] peers =  restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/stock/peers")
                            .queryParam("symbol", symbol)
                            .build())
                    .retrieve()
                    .body(String[].class);
            return peers == null ? List.of() : Arrays.asList(peers);
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch peers for " + symbol, e);
        }
    }

    public List<InsiderTransactionsResponse> getInsiderTransactions(String symbol){
        try{
            InsiderTransactionsWrapper transactions = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/stock/insider-transactions")
                            .queryParam("symbol", symbol)
                            .build())
                    .retrieve()
                    .body(InsiderTransactionsWrapper.class);

            return transactions == null ? List.of() : transactions.getData();
        }catch(RestClientException e){
            throw new ExternalApiException("Failed to fetch insider transactions for " + symbol, e);
        }
    }


}
