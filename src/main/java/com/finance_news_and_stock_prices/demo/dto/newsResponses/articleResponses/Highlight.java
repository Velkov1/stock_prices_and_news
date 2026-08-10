package com.finance_news_and_stock_prices.demo.dto.newsResponses.articleResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Highlight {
    private String highlight;
    private double sentiment;
    @JsonProperty("highlighted_in")
    private String highlightedIn;
}
