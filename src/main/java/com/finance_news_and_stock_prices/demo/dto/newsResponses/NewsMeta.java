package com.finance_news_and_stock_prices.demo.dto.newsResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsMeta {
    private int found;
    private int returned;
    private int limit;
    private int page;
}
