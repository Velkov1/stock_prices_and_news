package com.finance_news_and_stock_prices.demo.dto.stockResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinnhubQuoteResponse {
    @JsonProperty("c")
    private double currentPrice;

    @JsonProperty("d")
    private double change;

    @JsonProperty("dp")
    private double percentChange;

    @JsonProperty("h")
    private double highOfTheDay;

    @JsonProperty("l")
    private double lowOfTheDay;

    @JsonProperty("o")
    private double openPrice;

    @JsonProperty("pc")
    private double previousClosePrice;

    @JsonProperty("t")
    private long timestamp;

}
