package com.finance_news_and_stock_prices.demo.service;

import com.finance_news_and_stock_prices.demo.client.StockClient;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.*;
import com.finance_news_and_stock_prices.demo.exception.ExternalApiException;
import com.finance_news_and_stock_prices.demo.exception.NoSuchTickerException;
import com.finance_news_and_stock_prices.demo.model.PriceSnapshot;
import com.finance_news_and_stock_prices.demo.model.Ticker;
import com.finance_news_and_stock_prices.demo.repo.PriceSnapshotRepository;
import com.finance_news_and_stock_prices.demo.repo.TickerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class StockDataService {

    private StockClient stockClient;
    private final TickerService tickerService;
    private TickerRepository tickerRepository;
    private PriceSnapshotRepository priceSnapshotRepository;

    public StockDataService(StockClient stockClient, TickerService tickerService, TickerRepository tickerRepository, PriceSnapshotRepository priceSnapshotRepository) {
        this.stockClient = stockClient;
        this.tickerService = tickerService;
        this.tickerRepository = tickerRepository;
        this.priceSnapshotRepository = priceSnapshotRepository;
    }


    public StockPriceResponse getStockPrice(String symbol){
        Ticker stock = tickerService.getOrCreateTicker(symbol);
        TickerDTO tickerDTO = tickerService.toTickerDTO(stock);
        StockPriceResponse response = stockClient.getPrice(tickerDTO);
        Ticker ticker = tickerRepository.findByTicker(response.getTicker().getTicker())
                .orElseThrow(() -> new NoSuchTickerException(response.getTicker().getTicker()));
        priceSnapshotRepository.save(new PriceSnapshot(ticker, response.getPrice(), response.getDate()));
        return response;
    }


    public StockProfileResponse getStockProfile(String symbol){
        Ticker stock = tickerService.getOrCreateTicker(symbol);
        return stockClient.getProfileData(symbol);
    }

    public List<SummaryTickerDTO> getAllProfiles(){
        return stockClient.getAllProfiles();
    }



    public List<SummaryTickerDTO> getAllPeers(String symbol){
        Ticker stock = tickerService.getOrCreateTicker(symbol);
        List<String> peers = stockClient.getAllPeers(symbol);

        if(peers.isEmpty()){
            return List.of();
        }
        List<Ticker> existingTickers = tickerRepository.findByTickerIn(peers);
        Set<String> foundSymbols = existingTickers.stream()
                .map(Ticker :: getTicker)
                .collect(Collectors.toSet());
        List<Ticker> newlyCreatedTickers = peers.stream()
                .filter(s -> !foundSymbols.contains(s))
                .map(this::tryGetOrCreateTicker)
                .filter(Objects::nonNull)
                .toList();
        return Stream.concat(existingTickers.stream() , newlyCreatedTickers.stream())
                .map(t -> new SummaryTickerDTO(t.getTicker(), t.getCompanyName()))
                .toList();
    }

    private Ticker tryGetOrCreateTicker(String symbol){
        try {
            return tickerService.getOrCreateTicker(symbol);
        } catch (ExternalApiException | NoSuchTickerException e) {
            return null;
        }
    }

    public List<InsiderTransactionsResponse> getInsiderTransactions(String symbol) {
        tickerService.getOrCreateTicker(symbol);
        return stockClient.getInsiderTransactions(symbol);
    }


}
