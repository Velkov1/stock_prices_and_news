package com.finance_news_and_stock_prices.demo.dto.newsResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsArticle {
    private String uuid;
    private String title;
    private String description;
    private String keywords;
    private String snippet;
    private String url;
    @JsonProperty("image_url")
    private String imageUrl;
    private String language;
    @JsonProperty("published_at")
    private Instant publishedAt;
    private String source;
    @JsonProperty("relevance_score")
    private Double relevanceScore;
    private List<NewsArticle> entites;
    private List<Map<String, Object>> similar;
}
