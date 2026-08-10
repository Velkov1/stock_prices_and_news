package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinnhubSymbolResponse {
    private String currency;
    private String description;
    private String displaySymbol;
    private String figi;
    private String figiComposite;
    private String isin;
    private String mic;
    private String shareClassFIGI;
    private String symbol;
    private String symbol2;
    private String type;
}
