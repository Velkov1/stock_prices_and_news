package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsiderTransactionsResponse {

    private String memberName;
    private long share; // number of shares held after the transaction
    private long change; // number of share changed from the last period - positive : BUY, negative : SELL
    private String fillingDate;
    private String transactionDate;
    private String transactionCode;
    private double transactionPrice;

}
