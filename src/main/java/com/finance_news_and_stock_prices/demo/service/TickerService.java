package com.finance_news_and_stock_prices.demo.service;

import com.finance_news_and_stock_prices.demo.client.StockClient;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.StockProfileResponse;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.TickerDTO;
import com.finance_news_and_stock_prices.demo.exception.NoSuchTickerException;
import com.finance_news_and_stock_prices.demo.model.Ticker;
import com.finance_news_and_stock_prices.demo.repo.TickerRepository;
import org.springframework.stereotype.Service;

@Service
public class TickerService {
    private final TickerRepository tickerRepository;
    private final StockClient stockClient;

    public TickerService(TickerRepository tickerRepository, StockClient stockClient) {
        this.tickerRepository = tickerRepository;
        this.stockClient = stockClient;
    }

    public Ticker getOrCreateTicker(String symbol){
        String normalizedSymbol = symbol.trim().toUpperCase();
        return tickerRepository.findByTicker(normalizedSymbol)
                .orElseGet(() -> fetchAndSaveTicker(normalizedSymbol));
    }
    private Ticker fetchAndSaveTicker(String symbol){
        StockProfileResponse stock = stockClient.getProfileData(symbol);
        if(stock == null || stock.getTicker() == null){
            throw new NoSuchTickerException(symbol);
        }
        Ticker newTicker = new Ticker(
                stock.getTicker(),
                stock.getName(),
                stock.getCurrency(),
                stock.getLogoUrl(),
                stock.getWebUrl()
        );
        return tickerRepository.save(newTicker);
    }
    public TickerDTO toTickerDTO(Ticker ticker){
        return new TickerDTO(
                ticker.getId(),
                ticker.getTicker(),
                ticker.getCompanyName(),
                ticker.getCurrency(),
                ticker.getLogoUrl(),
                ticker.getWebUrl()
        );
    }
}
