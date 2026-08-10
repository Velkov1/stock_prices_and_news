package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinnhubProfileResponse {

    private String ticker;
    private String name;
    private String country;
    private String currency;
    private String estimateCurrency;
    private String exchange;
    private String ipo;
    private double marketCapitalization;
    private String logo;
    private double shareOutstanding;
    private String finnhubIndustry;
    private String phone;
    private String weburl;
    private double floatingShare;

}
