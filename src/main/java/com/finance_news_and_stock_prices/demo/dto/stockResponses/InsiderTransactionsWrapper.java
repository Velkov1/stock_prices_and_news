package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsiderTransactionsWrapper {
    private List<InsiderTransactionsResponse> data;
    private String symbol;

}
