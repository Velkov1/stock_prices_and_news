package com.finance_news_and_stock_prices.demo.dto.newsResponses.articleResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {
    private String symbol;
    private String name;
    private String exchange;
    @JsonProperty("exchange_long")
    private String exchangeLong;
    private String country;
    private String type;
    private String industry;
    @JsonProperty("match_score")
    private Double matchScore;
    private List<Highlight> highlights;

}
