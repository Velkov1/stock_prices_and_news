package com.finance_news_and_stock_prices.demo.repo;

import com.finance_news_and_stock_prices.demo.model.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {
    Optional<NewsItem> findByUrl(String url);
    List<NewsItem> findByTickerTicker(String symbol);
    List<NewsItem> findByPublishedAt(Instant date);
    List<NewsItem> findByPublishedAtAfter(Instant date);
    List<NewsItem> findByTickerTickerAndPublishedAtAfter(String symbol, Instant date);
    List<NewsItem> findByTickerTickerAndPublishedAt(String symbol, Instant date);
    List<NewsItem> findByHeadlineContainingIgnoreCase(String keyword);
}
