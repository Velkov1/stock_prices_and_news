package com.finance_news_and_stock_prices.demo.controller;

import com.finance_news_and_stock_prices.demo.dto.stockResponses.InsiderTransactionsResponse;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.StockPriceResponse;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.StockProfileResponse;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.SummaryTickerDTO;
import com.finance_news_and_stock_prices.demo.service.StockDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockDataController {
    private final StockDataService stockDataService;

    public StockDataController(StockDataService stockDataService){
        this.stockDataService  = stockDataService;
    }
    @GetMapping("/{symbol}/price")
    public StockPriceResponse getStockPrice(@PathVariable String symbol){
        return stockDataService.getStockPrice(symbol);
    }

    @GetMapping("/{symbol}/profile")
    public StockProfileResponse getStockProfile(@PathVariable String symbol){
        return stockDataService.getStockProfile(symbol);
    }

    @GetMapping("/{symbol}/peers")
    public List<SummaryTickerDTO> getAllPeers(@PathVariable String symbol){
        return stockDataService.getAllPeers(symbol);
    }

    @GetMapping("/{symbol}/insider-transactions")
    public List<InsiderTransactionsResponse> getInsiderTransactions(@PathVariable String symbol){
        return stockDataService.getInsiderTransactions(symbol);
    }
}