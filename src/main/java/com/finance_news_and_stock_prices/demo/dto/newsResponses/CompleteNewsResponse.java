package com.finance_news_and_stock_prices.demo.dto.newsResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteNewsResponse {
    private MetaResponse meta;
    private List<NewsArticle> data;
}
