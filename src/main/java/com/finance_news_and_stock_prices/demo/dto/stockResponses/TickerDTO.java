package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TickerDTO {
    private Long id;
    private String ticker;
    private String companyName;
    private String currency;
    private String logoUrl;
    private String webUrl;
}
