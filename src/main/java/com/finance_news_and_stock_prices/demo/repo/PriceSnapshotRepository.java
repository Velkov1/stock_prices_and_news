package com.finance_news_and_stock_prices.demo.repo;

import com.finance_news_and_stock_prices.demo.model.PriceSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceSnapshotRepository extends JpaRepository<PriceSnapshot, Long> {
    Optional<PriceSnapshot> findByTickerTicker(String symbol);

}
