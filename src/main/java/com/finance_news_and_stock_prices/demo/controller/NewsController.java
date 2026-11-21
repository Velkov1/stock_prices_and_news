package com.finance_news_and_stock_prices.demo.controller;

import com.finance_news_and_stock_prices.demo.dto.stockResponses.NewsItemDTO;
import com.finance_news_and_stock_prices.demo.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{symbol}")
    public List<NewsItemDTO> getNewsForTicker(@PathVariable String symbol){
        return newsService.getNewsForTicker(symbol);
    }

    @GetMapping
    public Map<String, List<NewsItemDTO>> getNewsForTickers(@RequestParam List<String> symbols){
        return newsService.getNewsForTickers(symbols);
    }

    @GetMapping("/{symbol}/positive")
    public List<NewsItemDTO> getPositiveNewsForTicker(@PathVariable String symbol){
        return newsService.getPositiveNewsForTicker(symbol);
    }

    @GetMapping("/{symbol}/negative")
    public List<NewsItemDTO> getNegativeNewsForTicker(@PathVariable String symbol){
        return newsService.getNegativeNewsForTicker(symbol);
    }

    @GetMapping("/search")
    public List<NewsItemDTO> searchNews(@RequestParam String keyword){
        return newsService.searchNews(keyword);
    }

    @GetMapping("/search/{symbol}/published-at")
    public List<NewsItemDTO> searchNewsForTickerPublishedAt(@PathVariable String symbol,
                                                            @RequestParam Instant date){
        return newsService.searchNewsForTickerPublishedAt(symbol, date);
    }

    @GetMapping("/search/published-at")
    public List<NewsItemDTO> searchNewsPublishedAt(@RequestParam Instant date){
        return newsService.searchNewsPublishedAt(date);
    }

    @GetMapping("/search/published-after")
    public List<NewsItemDTO> searchNewsPublishedAfter(@RequestParam Instant date){
        return newsService.searchNewsPublishedAfter(date);
    }

    @GetMapping("/search/{symbol}/stored")
    public List<NewsItemDTO> getAllStoredNewsForTicker(@PathVariable String symbol){
        return newsService.getAllStoredNewsForTicker(symbol);
    }
}
