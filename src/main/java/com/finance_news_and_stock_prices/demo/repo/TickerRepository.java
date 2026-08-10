package com.finance_news_and_stock_prices.demo.repo;

import com.finance_news_and_stock_prices.demo.model.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TickerRepository extends JpaRepository<Ticker, Long> {
    Optional<Ticker> findByTicker(String symbol);
    List<Ticker> findByTickerIn(List<String> symbols);
    Optional<Ticker> findByCompanyName(String companyName);
}
