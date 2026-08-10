package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class NewsItemDTO {
    private Long id;
    private TickerDTO ticker;
    private String headline;
    private String body;
    private String url;
    private String source;
    private Instant publishedAt;
}
