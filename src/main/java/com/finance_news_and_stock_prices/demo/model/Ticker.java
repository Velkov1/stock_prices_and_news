package com.finance_news_and_stock_prices.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;



@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter
    private String ticker;

    @Getter
    private String companyName;


    @Getter
    private String currency;

    @Getter
    private String logoUrl;

    @Getter
    private String webUrl;

    @Getter
    @Setter
    private Instant newsFetchedAt;


    public Ticker (String ticker, String companyName, String currency, String logoUrl, String webUrl){
        this.ticker = ticker;
        this.companyName = companyName;
        this.currency = currency;
        this.logoUrl = logoUrl;
        this.webUrl = webUrl;
    }
}
