package com.finance_news_and_stock_prices.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PriceSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticker_id")
    private Ticker ticker;

    private double price;
    private Instant date;

    public PriceSnapshot(Ticker ticker, double price, Instant date){
        this.ticker = ticker;
        this.price = price;
        this.date = date;
    }

}
