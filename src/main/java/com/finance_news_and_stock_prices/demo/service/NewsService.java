package com.finance_news_and_stock_prices.demo.service;

import com.finance_news_and_stock_prices.demo.client.NewsClient;
import com.finance_news_and_stock_prices.demo.dto.newsResponses.CompleteNewsResponse;
import com.finance_news_and_stock_prices.demo.dto.newsResponses.NewsArticle;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.NewsItemDTO;
import com.finance_news_and_stock_prices.demo.dto.stockResponses.TickerDTO;
import com.finance_news_and_stock_prices.demo.model.NewsItem;
import com.finance_news_and_stock_prices.demo.model.Ticker;
import com.finance_news_and_stock_prices.demo.repo.NewsItemRepository;
import com.finance_news_and_stock_prices.demo.repo.TickerRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsClient newsClient;
    private final TickerService tickerService;
    private final NewsItemRepository newsItemRepository;

    public NewsService(NewsClient newsClient, TickerService tickerService, NewsItemRepository newsItemRepository) {
        this.newsClient = newsClient;
        this.tickerService = tickerService;
        this.newsItemRepository = newsItemRepository;
    }


    public List<NewsItemDTO> getNewsForTicker(String symbol){
        Ticker ticker = tickerService.getOrCreateTicker(symbol);
        Instant cachedTime = Instant.now().minus(Duration.ofMinutes(30));
        List<NewsItem> cashed = newsItemRepository.findByTickerTickerAndPublishedAtAfter(symbol, cachedTime);
        if(!cashed.isEmpty()){
            return cashed
                    .stream()
                    .map(this::toNewsItemDTO)
                    .toList();
        }
        CompleteNewsResponse response = newsClient.getNewsForTicker(symbol);
        List<NewsItem> savedItems = response.getData().stream()
                .map(article -> saveIfNew(article, ticker))
                .toList();
        return savedItems.stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    private NewsItemDTO toNewsItemDTO(NewsItem news){
        return new NewsItemDTO(
                news.getId(),
                toTickerDTO(news.getTicker()),
                news.getHeadline(),
                news.getBody(),
                news.getUrl(),
                news.getSource(),
                news.getPublishedAt()
        );
    }
    private TickerDTO toTickerDTO(Ticker ticker){
        return new TickerDTO(
                ticker.getId(),
                ticker.getTicker(),
                ticker.getCompanyName(),
                ticker.getCurrency(),
                ticker.getLogoUrl(),
                ticker.getWebUrl()
        );
    }

    private NewsItem saveIfNew(NewsArticle article, Ticker ticker) {
        return newsItemRepository.findByUrl(article.getUrl())
                .orElseGet(() -> newsItemRepository.save(
                        new NewsItem(
                                ticker,
                                article.getTitle(),
                                article.getSnippet(),
                                article.getUrl(),
                                article.getSource(),
                                article.getPublishedAt()
                        )
                ));
    }

    public Map<String, List<NewsItemDTO>> getNewsForTickers(List<String> symbols){
        return symbols.stream()
                .collect(Collectors.toMap(
                        symbol -> symbol,
                        this::getNewsForTicker
                ));
    }

    public List<NewsItemDTO> getPositiveNewsForTicker(String symbol){
        Ticker ticker = tickerService.getOrCreateTicker(symbol);
        CompleteNewsResponse response = newsClient.getPositiveNews(symbol);
        List<NewsItem> savedItems = response.getData().stream()
                .map(article -> saveIfNew(article, ticker))
                .toList();
        return savedItems.stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    public List<NewsItemDTO> getNegativeNewsForTicker(String symbol){
        Ticker ticker = tickerService.getOrCreateTicker(symbol);
        CompleteNewsResponse response = newsClient.getNegativeNews(symbol);
        List<NewsItem> savedItems = response.getData().stream()
                .map(article -> saveIfNew(article, ticker))
                .toList();
        return savedItems.stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    //SEARCHES IN THE DB

    public List<NewsItemDTO> searchNews(String keyword) {
        return newsItemRepository.findByHeadlineContainingIgnoreCase(keyword)
                .stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    public List<NewsItemDTO> searchNewsForTickerPublishedAt(String symbol, Instant date){
        Ticker ticker = tickerService.getOrCreateTicker(symbol);
        List<NewsItem> news =  newsItemRepository.findByTickerTickerAndPublishedAt(symbol, date);
        return news.stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    public List<NewsItemDTO> searchNewsPublishedAt(Instant date){
        List<NewsItem> news =  newsItemRepository.findByPublishedAt(date);
        return news.stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    public List<NewsItemDTO> searchNewsPublishedAfter(Instant date){
        List<NewsItem> news =  newsItemRepository.findByPublishedAtAfter(date);
        return news.stream()
                .map(this::toNewsItemDTO)
                .toList();
    }

    public List<NewsItemDTO> getAllStoredNewsForTicker(String symbol) {
        return newsItemRepository.findByTickerTicker(symbol)
                .stream()
                .map(this::toNewsItemDTO)
                .toList();
    }
}
