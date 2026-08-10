package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockProfileResponse {
    private String ticker;
    private String name;
    private String country;
    private String currency;
    private double marketCap;
    private String logoUrl;
    private String webUrl;


}
