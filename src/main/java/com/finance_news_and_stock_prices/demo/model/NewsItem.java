package com.finance_news_and_stock_prices.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticker_id")
    private Ticker ticker;

    @Column(length = 1000)
    private String headline;
    @Column(length = 5000)
    private String body;
    @Column(length = 2000)
    private String url;
    private String source;

    private Instant publishedAt;

    public NewsItem(Ticker ticker, String headline, String body, String url, String source, Instant publishedAt) {
        this.ticker = ticker;
        this.headline = headline;
        this.body = body;
        this.url = url;
        this.source = source;
        this.publishedAt = publishedAt;
    }
}
