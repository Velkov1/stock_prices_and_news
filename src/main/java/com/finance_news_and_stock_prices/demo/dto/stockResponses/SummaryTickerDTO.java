package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryTickerDTO {
    private String tickerName;
    private String companyName;
}
